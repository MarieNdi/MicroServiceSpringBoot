package com.isi.school_management.user_service.mapper;

import com.isi.school_management.user_service.dto.TeacherDto;
import com.isi.school_management.user_service.entity.TeacherEntity;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TeacherMapper {
    @Mapping(target = "user", ignore = true)
    TeacherEntity teacherDtoToTeacherEntity(TeacherDto teacherDto);

    @Mapping(source = "user.emailPro", target = "user.emailPro")
    @Mapping(source = "user.token", target = "user.token")
    TeacherDto teacherEntityToTeacherDto(TeacherEntity teacherEntity);

    @Mapping(target = "user", ignore = true)
    void updateTeacherFromDto(TeacherDto teacherDto, @MappingTarget TeacherEntity teacherEntity);
}
