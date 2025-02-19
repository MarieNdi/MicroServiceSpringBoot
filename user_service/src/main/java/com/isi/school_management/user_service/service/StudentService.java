package com.isi.school_management.user_service.service;

import com.isi.school_management.user_service.dto.StudentDto;
import com.isi.school_management.user_service.entity.StudentEntity;
import com.isi.school_management.user_service.entity.UserEntity;
import com.isi.school_management.user_service.exception.ResourceNotFoundException;
import com.isi.school_management.user_service.mapper.StudentMapper;
import com.isi.school_management.user_service.repository.StudentRepository;
import com.isi.school_management.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    public StudentDto createStudent(StudentDto studentDto) {
        log.info("Création d'un nouvel étudiant");
        
        if (studentDto.user() == null || studentDto.user().emailPro() == null) {
            throw new IllegalArgumentException("Les informations utilisateur sont requises");
        }

        UserEntity userEntity = userRepository.findByEmailPro(studentDto.user().emailPro());
        if (userEntity == null) {
            throw new ResourceNotFoundException("Utilisateur non trouvé avec l'email: " + studentDto.user().emailPro());
        }

        if (studentRepository.existsByUser(userEntity)) {
            throw new IllegalStateException("Cet utilisateur est déjà associé à un étudiant");
        }

        StudentEntity studentEntity = studentMapper.studentDtoToStudentEntity(studentDto);
        studentEntity.setUser(userEntity);
        
        try {
            StudentEntity savedEntity = studentRepository.save(studentEntity);
            return studentMapper.studentEntityToStudentDto(savedEntity);
        } catch (Exception e) {
            log.error("Erreur lors de la création de l'étudiant", e);
            throw new RuntimeException("Erreur lors de la création de l'étudiant");
        }
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::studentEntityToStudentDto)
                .collect(Collectors.toList());
    }

    public StudentDto getStudentById(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé avec l'id: " + id));
        return studentMapper.studentEntityToStudentDto(studentEntity);
    }

    @Transactional
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        StudentEntity existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé avec l'id: " + id));
        
        studentMapper.updateStudentFromDto(studentDto, existingStudent);
        StudentEntity updatedEntity = studentRepository.save(existingStudent);
        return studentMapper.studentEntityToStudentDto(updatedEntity);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Étudiant non trouvé avec l'id: " + id);
        }
        studentRepository.deleteById(id);
    }
} 