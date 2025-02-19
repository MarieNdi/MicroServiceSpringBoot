package com.isi.school_management.user_service.mapper;

import com.isi.school_management.user_service.dto.StudentDto;
import com.isi.school_management.user_service.entity.StudentEntity;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses = {UserMapper.class},
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface StudentMapper {

    @Mapping(target = "user", ignore = true)
    StudentEntity studentDtoToStudentEntity(StudentDto studentDto);

    @Mapping(source = "user.emailPro", target = "user.emailPro")
    @Mapping(source = "user.token", target = "user.token")
    StudentDto studentEntityToStudentDto(StudentEntity studentEntity);

    @Mapping(target = "user", ignore = true)
    void updateStudentFromDto(StudentDto studentDto, @MappingTarget StudentEntity studentEntity);
}