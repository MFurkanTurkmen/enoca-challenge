package com.furkanturkmen.enocachallenge.service;

import com.furkanturkmen.enocachallenge.dto.request.CreateStoreRequestDto;
import com.furkanturkmen.enocachallenge.dto.request.UpdateStoreRequestDto;
import com.furkanturkmen.enocachallenge.dto.response.StoreResponseRecordDto;
import com.furkanturkmen.enocachallenge.exception.EnocaException;
import com.furkanturkmen.enocachallenge.exception.ErrorType;
import com.furkanturkmen.enocachallenge.mapper.IStoreMapper;
import com.furkanturkmen.enocachallenge.repository.IStoreRepository;
import com.furkanturkmen.enocachallenge.repository.entity.Store;
import com.furkanturkmen.enocachallenge.util.JwtTokenManager;
import com.furkanturkmen.enocachallenge.util.ServiceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StoreService extends ServiceManager<Store,String> {
    private final IStoreRepository storeRepository;
    private final JwtTokenManager jwtTokenManager;
    private final AuthService authService;
    private final ProductService productService;
    public StoreService(IStoreRepository storeRepository, JwtTokenManager jwtTokenManager, @Lazy AuthService authService,@Lazy ProductService productService) {
        super(storeRepository);
        this.storeRepository = storeRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.authService = authService;
        this.productService = productService;
    }

    public void register(CreateStoreRequestDto dto, String authId){
        Store store = IStoreMapper.INSTANCE.toStore(dto);
        store.setAuthId(authId);
        save(store);
        log.info("Store successfully created");
    }
    public Boolean updateStore(UpdateStoreRequestDto dto) {
        Optional<String> authId= jwtTokenManager.validToken(dto.getToken());
        Optional<Store> store = storeRepository.findOptionalByAuthId(authId.get());
        store.get().setPhone(dto.getPhone());
        store.get().setName(dto.getName());
        store.get().setAddress(dto.getAddress());
        update(store.get());
        log.info("Store successfully update");
        return true;
    }

    public List<StoreResponseRecordDto> findAllStores(String token) {
        Optional<String> authId= jwtTokenManager.validToken(token);
        if (authId.isEmpty()) throw new EnocaException(ErrorType.ID_NOT_FOUND);
        List<StoreResponseRecordDto> userResponseRecordDtos = new ArrayList<>();
        for (Store store: findAll()){
            userResponseRecordDtos.add(IStoreMapper.INSTANCE.toStoreResponseRecordDto(store));
        }
        return userResponseRecordDtos;
    }

    public Store findByAuthId(String authId) {
        return storeRepository.findOptionalByAuthId(authId).orElseThrow(()->new EnocaException(ErrorType.ID_NOT_FOUND));
    }

    public Boolean deleteStore(String token) {
        Optional<String> authId= jwtTokenManager.validToken(token);
        Optional<Store> store= storeRepository.findOptionalByAuthId(authId.get());
        productService.deleteAllProduct(store.get().getId());
        delete(store.get());
        authService.deleteById(authId.get());
        log.info("store deletion successful");
        return true;
    }

}
