package com.furkanturkmen.enocachallenge.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateProductRequestDto {
    private String token;
    private String productId;
    private String name;
    private String description;
    private String price;

}
