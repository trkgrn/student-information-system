package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.dto.StudentDto;
import com.trkgrn.studentinformationsystem.api.model.entity.Student;
import com.trkgrn.studentinformationsystem.api.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper modelMapper;

    public StudentController(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Optional<Student> studentFromDb = Optional.ofNullable(studentService.getStudentById(id));
        if (studentFromDb.isPresent()) {
            return new ResponseEntity<>(studentFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getStudentByUserId(@PathVariable Long userId) {
        Optional<Student> studentFromDb = Optional.ofNullable(studentService.getStudentByUserId(userId));
        if (studentFromDb.isPresent()) {
            return new ResponseEntity<>(studentFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @PostMapping
public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.saveStudent(student);
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable Long id) {
        Optional<Student> updatedStudent = Optional.ofNullable(studentService.updateStudent(student, id));
        if (updatedStudent.isPresent()) {
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id) {
        try {
            studentService.deleteStudentById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByJwt")
    public ResponseEntity<?> findByJwt(){
        Optional<Student> student = studentService.getAuthanticatedStudent();
        if (student.isPresent()) {
            return new ResponseEntity<>(modelMapper.map(student, StudentDto.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
