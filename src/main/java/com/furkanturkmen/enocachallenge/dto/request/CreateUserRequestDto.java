package com.furkanturkmen.enocachallenge.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateUserRequestDto {
    @NotBlank
    @Size(min = 3, max = 60)
    private String name;
    @Size(min = 3, max = 60)
    @NotBlank
    private String surname;
    @NotBlank
    @Email(message = "Gecersiz e-posta adresi")
    private String mail;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}