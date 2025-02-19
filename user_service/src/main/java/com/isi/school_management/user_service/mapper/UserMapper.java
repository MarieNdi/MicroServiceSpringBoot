package com.isi.school_management.user_service.mapper;

import com.isi.school_management.user_service.dto.UserDto;
import com.isi.school_management.user_service.entity.UserEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    void updateUserEntityFromUserDto(UserDto userDto, @MappingTarget UserEntity userEntity);

    @Mappings({
            @Mapping(source="emailPro",target="emailPro"),
            @Mapping(source="token",target="token")
    })
    UserDto userEntityToUserDto (UserEntity userEntity);

    UserEntity userDtoToUserEntity(UserDto userDto);
}