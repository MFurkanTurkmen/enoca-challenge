package com.furkanturkmen.enocachallenge.service;


import com.furkanturkmen.enocachallenge.dto.request.CreateUserRequestDto;
import com.furkanturkmen.enocachallenge.dto.request.UpdateUserRequestDto;
import com.furkanturkmen.enocachallenge.dto.response.UserResponseRecordDto;
import com.furkanturkmen.enocachallenge.exception.EnocaException;
import com.furkanturkmen.enocachallenge.exception.ErrorType;
import com.furkanturkmen.enocachallenge.mapper.IUserMapper;
import com.furkanturkmen.enocachallenge.repository.IUserRepository;
import com.furkanturkmen.enocachallenge.repository.entity.UserEntity;
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
public class UserService extends ServiceManager<UserEntity,String> {
    private final IUserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    private final AuthService authService;

    public UserService(IUserRepository userRepository, JwtTokenManager jwtTokenManager,@Lazy AuthService authService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.authService = authService;
    }

    public void register(CreateUserRequestDto dto,String authId){
        UserEntity user = IUserMapper.INSTANCE.toUserEntity(dto);
        user.setAuthId(authId);
        save(user);
        log.info("User successfully created");
    }

    public Boolean updateUser(UpdateUserRequestDto dto) {
        Optional<String> authId= jwtTokenManager.validToken(dto.getToken());
        Optional<UserEntity> userEntity =userRepository.findOptionalByAuthId(authId.get());
        userEntity.get().setName(dto.getName());
        userEntity.get().setSurname(dto.getSurname());
        update(userEntity.get());
        log.info("User successfully updated");
        return true;
    }

    public List<UserResponseRecordDto> findAllUsers(String token) {
        Optional<String> authId= jwtTokenManager.validToken(token);
        if (authId.isEmpty()) throw new EnocaException(ErrorType.ID_NOT_FOUND);
        List<UserResponseRecordDto> userResponseRecordDtos = new ArrayList<>();
        for (UserEntity user: findAll()){
            userResponseRecordDtos.add(IUserMapper.INSTANCE.toUserResponseRecordDto(user));
        }
        return userResponseRecordDtos;
    }

    public Boolean deleteUser(String token) {
        Optional<String> authId= jwtTokenManager.validToken(token);
        Optional<UserEntity> user= userRepository.findOptionalByAuthId(authId.get());
        delete(user.get());
        log.info("user deletion successful");
        authService.deleteById(authId.get());
        return true;
    }
}