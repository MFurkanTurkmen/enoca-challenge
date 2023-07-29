package com.furkanturkmen.enocachallenge.controller;

import com.furkanturkmen.enocachallenge.dto.request.CreateProductRequestDto;
import com.furkanturkmen.enocachallenge.dto.request.UpdateProductRequestDto;
import com.furkanturkmen.enocachallenge.dto.response.ProductForStoreResponseRecordDto;
import com.furkanturkmen.enocachallenge.dto.response.ProductForUsersResponseRecordDto;
import com.furkanturkmen.enocachallenge.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/")
    @Operation(summary = "Create Product",description = "The token must belong to a store")
    public ResponseEntity<Boolean> CreateProduct(CreateProductRequestDto dto){
        return ResponseEntity.ok(productService.createProduct(dto));
    }
    @PutMapping("/")
    @Operation(summary = "Update Product",description = "The token must belong to a store")
    public ResponseEntity<Boolean> UpdateProduct(UpdateProductRequestDto dto){
        return ResponseEntity.ok(productService.updateProduct(dto));
    }

    @GetMapping("/products-for-store/{token}")
    @Operation(summary = "the store's products",description = "The token must belong to a store")
    public ResponseEntity<List<ProductForStoreResponseRecordDto>> getStoreProduct(@PathVariable String token){
        return ResponseEntity.ok(productService.getProductsForStore(token));
    }
    @GetMapping("/products-for-users/{token}")
    @Operation(summary = "all products for users",description = "The token must belong to a user")
    public ResponseEntity<List<ProductForUsersResponseRecordDto>> getUsersProduct(@PathVariable String token){
        return ResponseEntity.ok(productService.getProductsForUsers(token));
    }

}
