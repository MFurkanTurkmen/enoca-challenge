package com.furkanturkmen.enocachallenge.dto.response;

import lombok.*;

@Builder
public record UserResponseRecordDto(String name, String surname) {
}
