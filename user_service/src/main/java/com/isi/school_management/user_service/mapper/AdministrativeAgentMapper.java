package com.isi.school_management.user_service.mapper;

import com.isi.school_management.user_service.dto.AdministrativeAgentDto;
import com.isi.school_management.user_service.entity.AdministrativeAgentEntity;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AdministrativeAgentMapper {

    @Mapping(target = "user", ignore = true)
    AdministrativeAgentEntity administrativeAgentDtoToAdministrativeAgentEntity(AdministrativeAgentDto administrativeAgentDto);

    @Mapping(source = "user.emailPro", target = "user.emailPro")
    @Mapping(source = "user.token", target = "user.token")
    AdministrativeAgentDto administrativeAgentEntityToAdministrativeAgentDto(AdministrativeAgentEntity administrativeAgentEntity);

    @Mapping(target = "user", ignore = true)
    void updateAdministrativeAgentFromDto(AdministrativeAgentDto administrativeAgentDto, @MappingTarget AdministrativeAgentEntity administrativeAgentEntity);
}