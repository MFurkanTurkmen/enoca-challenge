package com.furkanturkmen.enocachallenge.dto.response;

public record ProductForStoreResponseRecordDto(String id,
                                               String name,
                                               String description,
                                               String price,
                                               String category,
                                               int stock) {
}
