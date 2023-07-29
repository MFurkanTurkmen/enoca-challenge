package com.furkanturkmen.enocachallenge.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateStoreRequestDto {
    private String token;
    @NotBlank
    @Size(min = 3, max = 60)
    private String name;
    @NotBlank
    @Size(min = 6, max = 200)
    private String address;
    @NotBlank
   private String phone;
}
