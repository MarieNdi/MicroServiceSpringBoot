package com.isi.school_management.user_service.controller;

import com.isi.school_management.user_service.dto.StudentDto;
import com.isi.school_management.user_service.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) {
        StudentDto createdStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentDto student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDto studentDto) {
        StudentDto updatedStudent = studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}