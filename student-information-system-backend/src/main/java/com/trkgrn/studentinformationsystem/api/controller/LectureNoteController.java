package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.entity.LectureNote;
import com.trkgrn.studentinformationsystem.api.service.LectureNoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lecture-note")
public class LectureNoteController {

    private final LectureNoteService lectureNoteService;

    public LectureNoteController(LectureNoteService lectureNoteService) {
        this.lectureNoteService = lectureNoteService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getLectureNoteById(@PathVariable Long id) {
        Optional<LectureNote> lectureNoteFromDb = Optional.ofNullable(lectureNoteService.getLectureNoteById(id));
        if (lectureNoteFromDb.isPresent()) {
            return new ResponseEntity<>(lectureNoteFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLectureNotes() {
        return new ResponseEntity<>(lectureNoteService.getAllLectureNotes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveLectureNote(@RequestBody LectureNote lectureNote) {
        try {
            LectureNote savedLectureNote = lectureNoteService.saveLectureNote(lectureNote);
            return new ResponseEntity<>(savedLectureNote, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLectureNote(@RequestBody LectureNote lectureNote, @PathVariable Long id) {
        Optional<LectureNote> updatedLectureNote = Optional.ofNullable(lectureNoteService.updateLectureNote(lectureNote, id));
        if (updatedLectureNote.isPresent()) {
            return new ResponseEntity<>(updatedLectureNote, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLectureNoteById(@PathVariable Long id) {
        try {
            lectureNoteService.deleteLectureNoteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
