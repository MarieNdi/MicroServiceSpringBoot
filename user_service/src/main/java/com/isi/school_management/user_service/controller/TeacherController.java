package com.isi.school_management.user_service.controller;

import com.isi.school_management.user_service.dto.TeacherDto;
import com.isi.school_management.user_service.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        TeacherDto createdTeacher = teacherService.createTeacher(teacherDto);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
        TeacherDto teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherDto teacherDto) {
        TeacherDto updatedTeacher = teacherService.updateTeacher(id, teacherDto);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}