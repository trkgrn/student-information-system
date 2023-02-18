package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.Student;
import com.trkgrn.studentinformationsystem.api.model.entity.User;
import com.trkgrn.studentinformationsystem.api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserService userService;


    public StudentService(StudentRepository studentRepository, UserService userService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
    }

    public Student saveStudent(Student student) {
        student.setStudentNo(UUID.randomUUID().toString().substring(0, 9));
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student getStudentByUserId(Long userId) {
        return studentRepository.findByUser_UserId(userId).orElse(null);
    }

    public Optional<Student> getAuthanticatedStudent(){
        Optional<User> user = Optional.ofNullable(userService.findByJwt());
        if (user.isPresent()){
            Optional<Student> student = Optional.ofNullable(getStudentByUserId(user.get().getUserId()));
            return student;
        }
        return null;
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student, Long id) {
        Student studentOptional = studentRepository.findById(id).orElse(null);

        if (studentOptional != null) {
            Student updatedStudent = studentOptional;
            updatedStudent.setStudentId(id);
            updatedStudent.setStudentNo(student.getStudentNo());
            updatedStudent.setUser(student.getUser());
            return studentRepository.save(updatedStudent);
        }

        return null;
    }

    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
