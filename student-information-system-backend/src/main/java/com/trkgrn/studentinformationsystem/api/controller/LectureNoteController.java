package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.dto.LectureNoteDto;
import com.trkgrn.studentinformationsystem.api.model.entity.LectureNote;
import com.trkgrn.studentinformationsystem.api.service.LectureNoteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lecture-note")
public class LectureNoteController {

    private final LectureNoteService lectureNoteService;
    private final ModelMapper modelMapper;

    public LectureNoteController(LectureNoteService lectureNoteService, ModelMapper modelMapper) {
        this.lectureNoteService = lectureNoteService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getLectureNoteById(@PathVariable Long id) {
        Optional<LectureNote> lectureNoteFromDb = Optional.ofNullable(lectureNoteService.getLectureNoteById(id));
        if (lectureNoteFromDb.isPresent()) {
            return new ResponseEntity<>(modelMapper.map(lectureNoteFromDb.get(), LectureNoteDto.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLectureNotes() {
        List<LectureNote> lectureNotes = lectureNoteService.getAllLectureNotes();
        return new ResponseEntity<>(lectureNotes.stream()
                .map(lectureNote -> modelMapper.map(lectureNote, LectureNoteDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveLectureNote(@RequestBody LectureNote lectureNote) {
        try {
            LectureNote savedLectureNote = lectureNoteService.saveLectureNote(lectureNote);
            return new ResponseEntity<>(modelMapper.map(savedLectureNote, LectureNoteDto.class), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLectureNote(@RequestBody LectureNote lectureNote, @PathVariable Long id) {
        Optional<LectureNote> updatedLectureNote = Optional.ofNullable(lectureNoteService.updateLectureNote(lectureNote, id));
        if (updatedLectureNote.isPresent()) {
            return new ResponseEntity<>(modelMapper.map(updatedLectureNote.get(), LectureNoteDto.class), HttpStatus.OK);
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

    @GetMapping("/live-lesson/{liveLessonId}")
    public ResponseEntity<?> getLectureNoteByLiveLessonId(@PathVariable Long liveLessonId) {
        Optional<LectureNoteDto> lectureNoteFromDb = Optional.ofNullable(lectureNoteService.getLectureNoteByLiveLessonId(liveLessonId));
        if (lectureNoteFromDb.isPresent()) {
            return new ResponseEntity<>(lectureNoteFromDb.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
