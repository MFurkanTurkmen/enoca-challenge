package com.furkanturkmen.enocachallenge.service;

import com.furkanturkmen.enocachallenge.dto.request.CreateStoreRequestDto;
import com.furkanturkmen.enocachallenge.dto.request.CreateUserRequestDto;
import com.furkanturkmen.enocachallenge.dto.request.LoginRequestDto;
import com.furkanturkmen.enocachallenge.exception.EnocaException;
import com.furkanturkmen.enocachallenge.exception.ErrorType;
import com.furkanturkmen.enocachallenge.repository.IAuthRepository;
import com.furkanturkmen.enocachallenge.repository.entity.Auth;
import com.furkanturkmen.enocachallenge.util.JwtTokenManager;
import com.furkanturkmen.enocachallenge.util.ServiceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService extends ServiceManager<Auth,String> {
    private final IAuthRepository repository;
    private final UserService userService;
    private final StoreService storeService;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository repository, UserService userService, StoreService storeService, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.userService = userService;
        this.storeService = storeService;
        this.jwtTokenManager = jwtTokenManager;
    }

    public Boolean registerUser(CreateUserRequestDto dto){
        if (repository.findOptionalByMail(dto.getMail()).isPresent()) throw new EnocaException(ErrorType.MAIL_ALREADY_EXISTS);
        Auth auth=save(Auth.builder()
                .mail(dto.getMail())
                .password(dto.getPassword())
                .build());
        log.info("Auth successfully created");

        userService.register(dto,auth.getId());

        return true;
    }

    public Boolean registerStore(CreateStoreRequestDto dto) {
        if (repository.findOptionalByMail(dto.getMail()).isPresent()) throw new EnocaException(ErrorType.MAIL_ALREADY_EXISTS);
        Auth auth=save(Auth.builder()
                .mail(dto.getMail())
                .password(dto.getPassword())
                .build());
        storeService.register(dto,auth.getId());
        log.info("Auth successfully created");

        return true;
    }
    public String login(LoginRequestDto dto) {
        Auth auth=findByMail(dto.getMail());
        if (!auth.getPassword().equals(dto.getPassword())) throw new EnocaException(ErrorType.WRONG_PASSWORD);
        log.info("successful login");
        return jwtTokenManager.createToken(auth.getId()).get();
    }
    public Auth findByMail(String mail) {
        return repository.findOptionalByMail(mail).orElseThrow(() -> new EnocaException(ErrorType.MAIL_NOT_FOUND));
    }

}
