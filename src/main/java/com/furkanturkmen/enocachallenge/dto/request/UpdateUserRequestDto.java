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
public class UpdateUserRequestDto {
    private String token;
    @NotBlank
    @Size(min = 3, max = 60)
    private String name;
    @NotBlank
    @Size(min = 3, max = 60)
    private String surname;
}
