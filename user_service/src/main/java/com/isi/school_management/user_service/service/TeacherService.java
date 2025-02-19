package com.isi.school_management.user_service.service;

import com.isi.school_management.user_service.dto.TeacherDto;
import com.isi.school_management.user_service.entity.TeacherEntity;
import com.isi.school_management.user_service.entity.UserEntity;
import com.isi.school_management.user_service.exception.ResourceNotFoundException;
import com.isi.school_management.user_service.mapper.TeacherMapper;
import com.isi.school_management.user_service.repository.TeacherRepository;
import com.isi.school_management.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Transactional
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        log.info("Création d'un nouveau professeur");

        if (teacherDto.user() == null || teacherDto.user().emailPro() == null) {
            throw new IllegalArgumentException("Les informations utilisateur sont requises");
        }

        UserEntity userEntity = userRepository.findByEmailPro(teacherDto.user().emailPro());
        if (userEntity == null) {
            throw new ResourceNotFoundException("Utilisateur non trouvé avec l'email: " + teacherDto.user().emailPro());
        }

        if (teacherRepository.existsByUser(userEntity)) {
            throw new IllegalStateException("Cet utilisateur est déjà associé à un professeur");
        }

        TeacherEntity teacherEntity = teacherMapper.teacherDtoToTeacherEntity(teacherDto);
        teacherEntity.setUser(userEntity);

        try {
            TeacherEntity savedEntity = teacherRepository.save(teacherEntity);
            return teacherMapper.teacherEntityToTeacherDto(savedEntity);
        } catch (Exception e) {
            log.error("Erreur lors de la création du professeur", e);
            throw new RuntimeException("Erreur lors de la création du professeur");
        }
    }

    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacherMapper::teacherEntityToTeacherDto)
                .collect(Collectors.toList());
    }

    public TeacherDto getTeacherById(Long id) {
        TeacherEntity teacherEntity = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professeur non trouvé avec l'id: " + id));
        return teacherMapper.teacherEntityToTeacherDto(teacherEntity);
    }

    @Transactional
    public TeacherDto updateTeacher(Long id, TeacherDto teacherDto) {
        TeacherEntity existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professeur non trouvé avec l'id: " + id));

        teacherMapper.updateTeacherFromDto(teacherDto, existingTeacher);
        TeacherEntity updatedEntity = teacherRepository.save(existingTeacher);
        return teacherMapper.teacherEntityToTeacherDto(updatedEntity);
    }

    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Professeur non trouvé avec l'id: " + id);
        }
        teacherRepository.deleteById(id);
    }
}