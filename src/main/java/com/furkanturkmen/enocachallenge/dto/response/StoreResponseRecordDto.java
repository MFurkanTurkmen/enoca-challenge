package com.furkanturkmen.enocachallenge.dto.response;

import lombok.Builder;

@Builder
public record StoreResponseRecordDto(String name, String address, String phone) {
}
