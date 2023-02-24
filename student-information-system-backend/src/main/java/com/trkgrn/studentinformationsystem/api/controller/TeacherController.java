package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.dto.TeacherDto;
import com.trkgrn.studentinformationsystem.api.model.entity.Teacher;
import com.trkgrn.studentinformationsystem.api.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final ModelMapper modelMapper;

    public TeacherController(TeacherService teacherService, ModelMapper modelMapper) {
        this.teacherService = teacherService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        Optional<?> teacherFromDb = Optional.ofNullable(teacherService.getTeacherById(id));
        if (teacherFromDb.isPresent()) {
            return new ResponseEntity<>(teacherFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getStudentByUserId(@PathVariable Long userId) {
        Optional<Teacher> teacherFromDb = Optional.ofNullable(teacherService.getTeacherByUserId(userId));
        if (teacherFromDb.isPresent()) {
            return new ResponseEntity<>(teacherFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTeachers() {
        return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveTeacher(@RequestBody Teacher teacher) {
        try {
            Object savedTeacher = teacherService.saveTeacher(teacher);
            return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@RequestBody Teacher teacher, @PathVariable Long id) {
        Optional<?> updatedTeacher = Optional.ofNullable(teacherService.updateTeacher(teacher, id));
        if (updatedTeacher.isPresent()) {
            return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacherById(@PathVariable Long id) {
        try {
            teacherService.deleteTeacherById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByJwt")
    public ResponseEntity<?> findByJwt(){
        Optional<Teacher> teacher = teacherService.getAuthanticatedTeacher();
        if (teacher.isPresent()) {
            return new ResponseEntity<>(modelMapper.map(teacher.get(), TeacherDto.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
