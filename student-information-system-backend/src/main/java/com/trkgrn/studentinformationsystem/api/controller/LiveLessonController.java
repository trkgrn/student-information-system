package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.dto.LiveLessonDto;
import com.trkgrn.studentinformationsystem.api.model.entity.Student;
import com.trkgrn.studentinformationsystem.api.service.LiveLessonService;
import com.trkgrn.studentinformationsystem.api.model.entity.LiveLesson;
import com.trkgrn.studentinformationsystem.api.service.StudentService;
import com.trkgrn.studentinformationsystem.api.service.TeacherService;
import com.trkgrn.studentinformationsystem.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/live-lesson")
public class LiveLessonController {

    private final LiveLessonService liveLessonService;
    private final UserService userService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ModelMapper modelMapper;

    public LiveLessonController(LiveLessonService liveLessonService, UserService userService, TeacherService teacherService, StudentService studentService, ModelMapper modelMapper) {
        this.liveLessonService = liveLessonService;
        this.userService = userService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLiveLessonById(@PathVariable Long id) {
        Optional<LiveLesson> liveLessonFromDb = Optional.ofNullable(liveLessonService.getLiveLessonById(id));
        if (liveLessonFromDb.isPresent()) {
            return new ResponseEntity<>(liveLessonFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLiveLessons() {
        Optional<List<LiveLesson>> liveLessonsFromDb = Optional.ofNullable(liveLessonService.getAllLiveLessons());
        if (liveLessonsFromDb.isPresent()) {
            return new ResponseEntity<>(liveLessonsFromDb.get()
                    .stream()
                    .map(iter -> modelMapper.map(iter, LiveLessonDto.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        return new ResponseEntity<>(liveLessonService.getAllLiveLessons(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveLiveLesson(@RequestBody LiveLesson liveLesson) {
        try {
            LiveLesson savedLiveLesson = liveLessonService.saveLiveLesson(liveLesson);
            return new ResponseEntity<>(savedLiveLesson, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLiveLesson(@RequestBody LiveLesson liveLesson, @PathVariable Long id) {
        Optional<LiveLesson> updatedLiveLesson = Optional.ofNullable(liveLessonService.updateLiveLesson(liveLesson, id));
        if (updatedLiveLesson.isPresent()) {
            return new ResponseEntity<>(updatedLiveLesson, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLiveLessonById(@PathVariable Long id) {
        try {
            liveLessonService.deleteLiveLessonById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teacher/{userId}")
    public ResponseEntity<?> getLiveLessonByUserId(@PathVariable Long userId) {
        Optional<List<LiveLesson>> liveLessonFromDb = Optional.ofNullable(liveLessonService.getLiveLessonByTeacher_User_UserId(userId));
        if (liveLessonFromDb.isPresent()) {
            return new ResponseEntity<>(liveLessonFromDb.get()
                    .stream()
                    .map(iter -> modelMapper.map(iter, LiveLessonDto.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/student/{userId}")
    public ResponseEntity<?> getLiveLessonByStudentUserId(@PathVariable Long userId) {
        Optional<List<LiveLesson>> liveLessonFromDb = Optional.ofNullable(liveLessonService.getLiveLessonByStudent_User_UserId(userId));
        if (liveLessonFromDb.isPresent()) {
            return new ResponseEntity<>(liveLessonFromDb.get()
                    .stream()
                    .map(iter -> modelMapper.map(iter, LiveLessonDto.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveLiveLessons() {
        Optional<List<LiveLesson>> liveLessonFromDb = Optional.ofNullable(liveLessonService.getLiveLessonByIsActiveIsTrue());
        if (liveLessonFromDb.isPresent()) {
            return new ResponseEntity<>(liveLessonFromDb.get()
                    .stream()
                    .map(iter -> modelMapper.map(iter, LiveLessonDto.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableLiveLessonsByStudent() {
        Optional<Student> student = studentService.getAuthanticatedStudent();
        if (student.isPresent()) {
            Optional<List<LiveLesson>> liveLessonFromDb = Optional.ofNullable(liveLessonService.getAvailableLiveLessonsByStudent(student.get()));
            if (liveLessonFromDb.isPresent()) {
                return new ResponseEntity<>(liveLessonFromDb.get()
                        .stream()
                        .map(iter -> modelMapper.map(iter, LiveLessonDto.class))
                        .collect(Collectors.toList()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
