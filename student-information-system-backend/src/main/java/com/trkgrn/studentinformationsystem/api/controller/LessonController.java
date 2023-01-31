package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.entity.Lesson;
import com.trkgrn.studentinformationsystem.api.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLessonById(@PathVariable Long id) {
        Optional<Lesson> lessonFromDb = Optional.ofNullable(lessonService.getLessonById(id));
        if (lessonFromDb.isPresent()) {
            return new ResponseEntity<>(lessonFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLessons() {
        return new ResponseEntity<>(lessonService.getAllLessons(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveLesson(@RequestBody Lesson lesson) {
        try {
            Lesson savedLesson = lessonService.saveLesson(lesson);
            return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLesson(@RequestBody Lesson lesson, @PathVariable Long id) {
        Optional<Lesson> updatedLesson = Optional.ofNullable(lessonService.updateLesson(lesson, id));
        if (updatedLesson.isPresent()) {
            return new ResponseEntity<>(updatedLesson, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLessonById(@PathVariable Long id) {
        try {
            lessonService.deleteLessonById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
