package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.Teacher;
import com.trkgrn.studentinformationsystem.api.model.entity.User;
import com.trkgrn.studentinformationsystem.api.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserService userService;

    public TeacherService(TeacherRepository teacherRepository, UserService userService) {
        this.teacherRepository = teacherRepository;
        this.userService = userService;
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public Teacher getTeacherByUserId(Long userId) {
        return teacherRepository.findByUser_UserId(userId).orElse(null);
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    public Teacher updateTeacher(Teacher teacher, Long id) {
        Teacher teacherOptional = teacherRepository.findById(id).orElse(null);

        if (teacherOptional != null) {
            Teacher updatedTeacher = teacherOptional;
            updatedTeacher.setTeacherId(id);
            updatedTeacher.setUser(teacher.getUser());
            return teacherRepository.save(updatedTeacher);
        }

        return null;
    }

    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getAuthanticatedTeacher(){
        Optional<User> user = Optional.ofNullable(userService.findByJwt());
        if (user.isPresent()){
            Optional<Teacher> teacher = Optional.ofNullable(getTeacherByUserId(user.get().getUserId()));
            return teacher;
        }
        return null;
    }
}
