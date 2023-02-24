package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.dto.NoteDto;
import com.trkgrn.studentinformationsystem.api.model.dto.SemesterDto;
import com.trkgrn.studentinformationsystem.api.model.entity.Note;
import com.trkgrn.studentinformationsystem.api.service.NoteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    private final NoteService noteService;
    private final ModelMapper modelMapper;

    public NoteController(NoteService noteService, ModelMapper modelMapper) {
        this.noteService = noteService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable Long id) {
        Optional<Note> noteFromDb = Optional.ofNullable(noteService.getNoteById(id));
        if (noteFromDb.isPresent()) {
            return new ResponseEntity<>(noteFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllNotes() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveNote(@RequestBody Note note) {
        try {
            Note savedNote = noteService.saveNote(note);
            return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@RequestBody Note note, @PathVariable Long id) {
        Optional<Note> updatedNote = Optional.ofNullable(noteService.updateNote(note, id));
        if (updatedNote.isPresent()) {
            return new ResponseEntity<>(updatedNote, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNoteById(@PathVariable Long id) {
        try {
            noteService.deleteNoteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student/{studentId}/livelesson/active")
    public ResponseEntity<?> getNotesByStudent_StudentIdAndLiveLesson_IsActiveIsTrue(@PathVariable Long studentId) {
        Optional<List<Note>> notes = noteService.getNotesByStudent_StudentIdAndLiveLesson_IsActiveIsTrue(studentId);
        if (notes.isPresent()) {
            return new ResponseEntity<>(notes.get()
                    .stream()
                    .map(it->modelMapper.map(it, NoteDto.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/student/{studentId}/livelesson/educationseason/{educationSeasonId}")
    public ResponseEntity<?> getNotesByStudent_StudentIdAndLiveLesson_EducationSeason_Name(@PathVariable Long studentId, @PathVariable Long educationSeasonId) {
        Optional<List<Note>> notes = noteService.getNotesByStudent_StudentIdAndLiveLesson_EducationSeason_Name(studentId, educationSeasonId);
        if (notes.isPresent()) {
            return new ResponseEntity<>(notes.get()
                    .stream()
                    .map(it->modelMapper.map(it,NoteDto.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/student/{studentId}/semester")
    public ResponseEntity<?> getSemestersByStudent_StudentId(@PathVariable Long studentId) {
        Optional<List<SemesterDto>> semesters = noteService.getSemestersByStudent_StudentId(studentId);
        if (semesters.isPresent()) {
            return new ResponseEntity<>(semesters.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/student/{studentId}/educationseason/{educationSeasonId}/period/{periodId}")
    public ResponseEntity<?> getNotesByStudentIdAndSemester(@PathVariable Long studentId, @PathVariable Long educationSeasonId, @PathVariable Long periodId) {
        Optional<List<Note>> notes = noteService.getNotesByStudentIdAndSemester(studentId, educationSeasonId, periodId);
        if (notes.isPresent()) {
            return new ResponseEntity<>(notes.get()
                    .stream()
                    .map(it->modelMapper.map(it,NoteDto.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
