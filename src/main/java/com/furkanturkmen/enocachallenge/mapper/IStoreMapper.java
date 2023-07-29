package com.furkanturkmen.enocachallenge.mapper;

import com.furkanturkmen.enocachallenge.dto.request.CreateStoreRequestDto;
import com.furkanturkmen.enocachallenge.dto.response.StoreResponseRecordDto;
import com.furkanturkmen.enocachallenge.repository.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IStoreMapper {
    IStoreMapper INSTANCE = Mappers.getMapper(IStoreMapper.class);
    Store toStore (final CreateStoreRequestDto dto);
    StoreResponseRecordDto toStoreResponseRecordDto(final Store store);
}
