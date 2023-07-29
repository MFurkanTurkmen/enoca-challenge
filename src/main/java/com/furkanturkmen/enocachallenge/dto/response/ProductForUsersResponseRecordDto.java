package com.furkanturkmen.enocachallenge.dto.response;

public record ProductForUsersResponseRecordDto(String name,
                                               String description,
                                               String price,
                                               String category,
                                               int stock) {
}
