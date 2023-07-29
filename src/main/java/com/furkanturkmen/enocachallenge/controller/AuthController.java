package com.furkanturkmen.enocachallenge.controller;

import com.furkanturkmen.enocachallenge.dto.request.CreateStoreRequestDto;
import com.furkanturkmen.enocachallenge.dto.request.CreateUserRequestDto;
import com.furkanturkmen.enocachallenge.dto.request.LoginRequestDto;
import com.furkanturkmen.enocachallenge.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpLogging;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/user")
    @Operation(summary = "user register.")
    public ResponseEntity<Boolean> registerUser(@Valid @RequestBody CreateUserRequestDto dto){
        return ResponseEntity.ok(authService.registerUser(dto));
    }
    @PostMapping("/store")
    @Operation(summary = "store register.")
    public ResponseEntity<Boolean> registerStore(@Valid @RequestBody CreateStoreRequestDto dto){
        return ResponseEntity.ok(authService.registerStore(dto));
    }
    @PostMapping("/login")
    @Operation(summary = "login.")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }


}
