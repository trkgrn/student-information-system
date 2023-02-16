package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.entity.LessonRequest;
import com.trkgrn.studentinformationsystem.api.service.LessonRequestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/lesson-request")
public class LessonRequestController {

    private final LessonRequestService lessonRequestService;

    public LessonRequestController(LessonRequestService lessonRequestService) {
        this.lessonRequestService = lessonRequestService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLessonRequestById(@PathVariable Long id) {
        Optional<LessonRequest> lessonRequestFromDb = Optional.ofNullable(lessonRequestService.getLessonRequestById(id));
        if (lessonRequestFromDb.isPresent()) {
            return new ResponseEntity<>(lessonRequestFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLessonRequests() {
        return new ResponseEntity<>(lessonRequestService.getAllLessonRequests(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveLessonRequest(@RequestBody LessonRequest lessonRequest) {
        try {
            LessonRequest savedLessonRequest = lessonRequestService.saveLessonRequest(lessonRequest);
            return new ResponseEntity<>(savedLessonRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLessonRequest(@RequestBody LessonRequest lessonRequest, @PathVariable Long id) {
        Optional<LessonRequest> updatedLessonRequest = Optional.ofNullable(lessonRequestService.updateLessonRequest(lessonRequest, id));
        if (updatedLessonRequest.isPresent()) {
            return new ResponseEntity<>(updatedLessonRequest, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLessonRequestById(@PathVariable Long id) {
        try {
            lessonRequestService.deleteLessonRequestById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
