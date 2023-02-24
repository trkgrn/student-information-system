package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.entity.LetterGrade;
import com.trkgrn.studentinformationsystem.api.service.LetterGradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/letter-grade")
public class LetterGradeController {

    private final LetterGradeService letterGradeService;

    public LetterGradeController(LetterGradeService letterGradeService) {
        this.letterGradeService = letterGradeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLetterGradeById(@PathVariable Long id) {
        Optional<LetterGrade> letterGradeFromDb = Optional.ofNullable(letterGradeService.getLetterGradeById(id));
        if (letterGradeFromDb.isPresent()) {
            return new ResponseEntity<>(letterGradeFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLetterGrades() {
        return new ResponseEntity<>(letterGradeService.getAllLetterGrades(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveLetterGrade(@RequestBody LetterGrade letterGrade) {
        try {
            LetterGrade savedLetterGrade = letterGradeService.saveLetterGrade(letterGrade);
            return new ResponseEntity<>(savedLetterGrade, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLetterGrade(@RequestBody LetterGrade letterGrade, @PathVariable Long id) {
        Optional<LetterGrade> updatedLetterGrade = Optional.ofNullable(letterGradeService.updateLetterGrade(letterGrade, id));
        if (updatedLetterGrade.isPresent()) {
            return new ResponseEntity<>(updatedLetterGrade, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLetterGradeById(@PathVariable Long id) {
        try {
            letterGradeService.deleteLetterGradeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
