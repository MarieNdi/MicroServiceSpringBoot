package com.isi.school_management.user_service.mapper;

import com.isi.school_management.user_service.dto.UserDto;
import com.isi.school_management.user_service.entity.UserEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Important pour l'injection Spring
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(target = "id", ignore = true) // Ignorer l'ID car il ne doit pas être modifié
    void updateUserEntityFromUserDto(UserDto userDto, @MappingTarget UserEntity userEntity);

    @Mappings({
            @Mapping(source="userEntity.emailPro",target="emailPro"),
            @Mapping(source="userEntity.token",target="token")
    })

    UserDto userEntityToUserDto (UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity userDtoToUserEntity(UserDto userDto);





}
