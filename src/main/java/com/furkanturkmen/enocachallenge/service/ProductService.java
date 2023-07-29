package com.furkanturkmen.enocachallenge.service;

import com.furkanturkmen.enocachallenge.dto.request.CreateProductRequestDto;
import com.furkanturkmen.enocachallenge.dto.request.UpdateProductRequestDto;
import com.furkanturkmen.enocachallenge.dto.response.ProductForStoreResponseRecordDto;
import com.furkanturkmen.enocachallenge.dto.response.ProductForUsersResponseRecordDto;
import com.furkanturkmen.enocachallenge.exception.EnocaException;
import com.furkanturkmen.enocachallenge.exception.ErrorType;
import com.furkanturkmen.enocachallenge.mapper.IProductMapper;
import com.furkanturkmen.enocachallenge.repository.IProductRepository;
import com.furkanturkmen.enocachallenge.repository.entity.Product;
import com.furkanturkmen.enocachallenge.repository.entity.Store;
import com.furkanturkmen.enocachallenge.util.JwtTokenManager;
import com.furkanturkmen.enocachallenge.util.ServiceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService extends ServiceManager<Product,String> {
    private final IProductRepository productRepository;
    private final StoreService storeService;
    private final JwtTokenManager jwtTokenManager;

    public ProductService(IProductRepository productRepository, StoreService storeService, JwtTokenManager jwtTokenManager) {
        super(productRepository);
        this.productRepository = productRepository;
        this.storeService = storeService;
        this.jwtTokenManager = jwtTokenManager;
    }
    public Boolean createProduct(CreateProductRequestDto dto){
        Optional<String> authId= jwtTokenManager.validToken(dto.getToken());
        if (authId.isEmpty()) throw new EnocaException(ErrorType.ID_NOT_FOUND);
        Store store=storeService.findByAuthId(authId.get());
        Product product=IProductMapper.INSTANCE.toProduct(dto);
        product.setStoreId(store.getId());
        save(product);
        log.info("Product created");
        return true;
    }
    public Boolean updateProduct(UpdateProductRequestDto dto){
        Optional<String> authId= jwtTokenManager.validToken(dto.getToken());
        if (authId.isEmpty()) throw new EnocaException(ErrorType.ID_NOT_FOUND);
        Product product=findById(dto.getProductId()).orElseThrow(()->new EnocaException(ErrorType.PRODUCT_NOT_FOUND));
        product.setDescription(dto.getDescription());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        update(product);
        log.info("Product updated");
        return true;
    }

    public List<ProductForStoreResponseRecordDto> getProductsForStore(String token) {
        Optional<String> authId=jwtTokenManager.validToken(token);
        if(authId.isEmpty()) throw new EnocaException(ErrorType.ID_NOT_FOUND);
        Store store=storeService.findByAuthId(authId.get());
        List<Product> products=productRepository.findOptionalByStoreId(store.getId())
                .orElseThrow(()->new EnocaException(ErrorType.PRODUCT_NOT_FOUND));
        return products.stream().map(x->IProductMapper.INSTANCE.toProductForStoreResponseRecordDto(x)).toList();
    }

    public List<ProductForUsersResponseRecordDto> getProductsForUsers(String token) {
        Optional<String> authId=jwtTokenManager.validToken(token);
        if(authId.isEmpty()) throw new EnocaException(ErrorType.ID_NOT_FOUND);
        List<ProductForUsersResponseRecordDto> products= new ArrayList<>();
        for (Product product: findAll()){
            products.add(IProductMapper.INSTANCE.toProductForUsersResponseRecordDto(product));
        }
        return products;
    }
    public void deleteProduct(String token,String productId){
        Optional<String> authId=jwtTokenManager.validToken(token);
        if(authId.isEmpty()) throw new EnocaException(ErrorType.ID_NOT_FOUND);
        Product product=findById(productId).orElseThrow(()->new EnocaException(ErrorType.PRODUCT_NOT_FOUND));
        delete(product);
        log.info("Product deleted");
    }

    public void deleteAllProduct(String storeId){
        List<Product> products=productRepository.findOptionalByStoreId(storeId).orElseThrow(()->new EnocaException(ErrorType.PRODUCT_NOT_FOUND));
        for (Product product: products){
            delete(product);
            log.info(" The product with id "+product.getId()+" has been deleted");
        }
        log.info("Product deleted");
    }
}
