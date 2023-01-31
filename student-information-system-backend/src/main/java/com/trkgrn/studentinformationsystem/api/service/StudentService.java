package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.Student;
import com.trkgrn.studentinformationsystem.api.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
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
