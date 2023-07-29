package com.furkanturkmen.enocachallenge.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateStoreRequestDto {
    @NotBlank
    @Size(min = 3, max = 60)
    private String name;
    @NotBlank
    @Email(message = "Gecersiz e-posta adresi")
    private String mail;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    @NotBlank
    @Size(min = 6, max = 60)
    private String address;
    @NotBlank
    private String phone;

}
