package com.furkanturkmen.enocachallenge.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateProductRequestDto {
    @NotBlank
    private String token;
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;
    private String description;
    @NotBlank
    @Size(min = 1, max = 12)
    private String price;
    @NotBlank
    private String category;
    @NotBlank
    private int stock;
}
