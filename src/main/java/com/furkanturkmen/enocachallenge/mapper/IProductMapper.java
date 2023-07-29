package com.furkanturkmen.enocachallenge.mapper;

import com.furkanturkmen.enocachallenge.dto.request.CreateProductRequestDto;
import com.furkanturkmen.enocachallenge.dto.response.ProductForStoreResponseRecordDto;
import com.furkanturkmen.enocachallenge.dto.response.ProductForUsersResponseRecordDto;
import com.furkanturkmen.enocachallenge.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper {
    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);
    Product toProduct(final CreateProductRequestDto dto);
    ProductForStoreResponseRecordDto toProductForStoreResponseRecordDto(final Product product);
    ProductForUsersResponseRecordDto toProductForUsersResponseRecordDto(final Product product);

}
