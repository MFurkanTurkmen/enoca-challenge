package com.furkanturkmen.enocachallenge.controller;

import com.furkanturkmen.enocachallenge.dto.request.CreateUserRequestDto;
import com.furkanturkmen.enocachallenge.dto.request.UpdateUserRequestDto;
import com.furkanturkmen.enocachallenge.dto.response.UserResponseRecordDto;
import com.furkanturkmen.enocachallenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PutMapping("/")
    @Operation(summary = "Update user.")
    public ResponseEntity<Boolean> updateUser(@RequestBody UpdateUserRequestDto dto){
        return ResponseEntity.ok(userService.updateUser(dto));
    }
    @GetMapping("/users/{token}")
    @Operation(summary = "Find all users.")
    public ResponseEntity<List<UserResponseRecordDto>> findAll(@PathVariable("token") String token){
        return ResponseEntity.ok(userService.findAllUsers(token));
    }

    @DeleteMapping("/{token}")
    @Operation(summary = "Delete user.")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("token") String token){
        return ResponseEntity.ok(userService.deleteUser(token));
    }

}
