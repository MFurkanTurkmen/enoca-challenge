package com.furkanturkmen.enocachallenge.controller;

import com.furkanturkmen.enocachallenge.dto.request.UpdateStoreRequestDto;
import com.furkanturkmen.enocachallenge.dto.response.StoreResponseRecordDto;
import com.furkanturkmen.enocachallenge.dto.response.UserResponseRecordDto;
import com.furkanturkmen.enocachallenge.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/store")
@RequiredArgsConstructor
@RestController
public class StoreController {
    private final StoreService storeService;

    @PutMapping("/")
    @Operation(summary = "Update store.")
    public ResponseEntity<Boolean> updateStore(UpdateStoreRequestDto dto){
        return ResponseEntity.ok(storeService.updateStore(dto));
    }
    @GetMapping("/stores/{token}")
    @Operation(summary = "Find all stores.")
    public ResponseEntity<List<StoreResponseRecordDto>> findAll(@PathVariable("token") String token){
        return ResponseEntity.ok(storeService.findAllStores(token));
    }
    @DeleteMapping("/{token}")
    @Operation(summary = "Delete store.")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("token") String token){
        return ResponseEntity.ok(storeService.deleteStore(token));
    }
}
