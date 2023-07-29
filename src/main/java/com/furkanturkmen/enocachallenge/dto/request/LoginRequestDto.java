package com.furkanturkmen.enocachallenge.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequestDto {
    private String mail;
    private String password;
}
