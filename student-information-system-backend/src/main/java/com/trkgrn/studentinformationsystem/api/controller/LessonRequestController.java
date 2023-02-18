package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.dto.LessonRequestDto;
import com.trkgrn.studentinformationsystem.api.model.entity.LessonRequest;
import com.trkgrn.studentinformationsystem.api.model.entity.Note;
import com.trkgrn.studentinformationsystem.api.service.LessonRequestService;
import com.trkgrn.studentinformationsystem.api.service.NoteService;
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
@RequestMapping("/api/lesson-request")
public class LessonRequestController {

    private final LessonRequestService lessonRequestService;
    private final ModelMapper modelMapper;
    private final NoteService noteService;

    public LessonRequestController(LessonRequestService lessonRequestService, ModelMapper modelMapper, NoteService noteService) {
        this.lessonRequestService = lessonRequestService;
        this.modelMapper = modelMapper;
        this.noteService = noteService;
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
        Optional<List<LessonRequest>> lessonRequestsFromDb = Optional.ofNullable(lessonRequestService.getAllLessonRequests());
        if (lessonRequestsFromDb.isPresent()) {
            return new ResponseEntity<>(lessonRequestsFromDb.get()
                    .stream()
                    .map(lessonRequest -> modelMapper.map(lessonRequest, LessonRequestDto.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> saveLessonRequest(@RequestBody LessonRequest lessonRequest) {
        try {
            LessonRequest savedLessonRequest = lessonRequestService.saveLessonRequest(lessonRequest);
            return new ResponseEntity<>(modelMapper.map(savedLessonRequest, LessonRequestDto.class), HttpStatus.CREATED);
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

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getLessonRequestByStudent_StudentId(@PathVariable Long studentId) {
        Optional<List<LessonRequest>> lessonRequestFromDb = Optional.ofNullable(lessonRequestService.getLessonRequestByStudent_StudentId(studentId));
        if (lessonRequestFromDb.isPresent()) {
            return new ResponseEntity<>(lessonRequestFromDb.get()
                    .stream()
                    .map(it -> modelMapper.map(it, LessonRequestDto.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/waiting")
    public ResponseEntity<?> getLessonRequestsByIsApprovedIsNull() {
        Optional<List<LessonRequest>> lessonRequestFromDb = Optional.ofNullable(lessonRequestService.getLessonRequestsByIsApprovedIsNull());
        if (lessonRequestFromDb.isPresent()) {
            return new ResponseEntity<>(lessonRequestFromDb.get()
                    .stream()
                    .map(it -> modelMapper.map(it, LessonRequestDto.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveLessonRequest(@PathVariable Long id) {
        Optional<LessonRequest> lessonRequestFromDb = Optional.ofNullable(lessonRequestService.approveLessonRequest(id));
        if (lessonRequestFromDb.isPresent()) {
            LessonRequest request = lessonRequestFromDb.get();
            Note newNote = noteService.saveNote(new Note(0L, "STARTED", null, null, null,
                    null, request.getStudent(), request.getLiveLesson()));
            return new ResponseEntity<>(modelMapper.map(lessonRequestFromDb,LessonRequestDto.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectLessonRequest(@PathVariable Long id) {
        Optional<LessonRequest> lessonRequestFromDb = Optional.ofNullable(lessonRequestService.rejectLessonRequest(id));
        if (lessonRequestFromDb.isPresent()) {
            return new ResponseEntity<>(modelMapper.map(lessonRequestFromDb,LessonRequestDto.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
