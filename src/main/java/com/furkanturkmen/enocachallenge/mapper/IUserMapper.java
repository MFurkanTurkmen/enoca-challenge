package com.furkanturkmen.enocachallenge.mapper;

import com.furkanturkmen.enocachallenge.dto.request.CreateUserRequestDto;
import com.furkanturkmen.enocachallenge.dto.response.UserResponseRecordDto;
import com.furkanturkmen.enocachallenge.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserEntity toUserEntity(final CreateUserRequestDto dto);
    UserResponseRecordDto toUserResponseRecordDto(final UserEntity entity);
}
