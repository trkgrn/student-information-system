package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.entity.File;
import com.trkgrn.studentinformationsystem.api.model.entity.WeeklyNotes;
import com.trkgrn.studentinformationsystem.api.service.WeeklyNotesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/weekly-notes")
public class WeeklyNotesController {

    private final WeeklyNotesService weeklyNotesService;

    public WeeklyNotesController(WeeklyNotesService weeklyNotesService) {
        this.weeklyNotesService = weeklyNotesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWeeklyNotesById(@PathVariable Long id) {
        Optional<WeeklyNotes> weeklyNotesFromDb = Optional.ofNullable(weeklyNotesService.getWeeklyNotesById(id));
        if (weeklyNotesFromDb.isPresent()) {
            return new ResponseEntity<>(weeklyNotesFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllWeeklyNotes() {
        return new ResponseEntity<>(weeklyNotesService.getAllWeeklyNotes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveWeeklyNotes(@RequestBody WeeklyNotes weeklyNotes) {
        try {
            WeeklyNotes savedWeeklyNotes = weeklyNotesService.saveWeeklyNotes(weeklyNotes);
            return new ResponseEntity<>(savedWeeklyNotes, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWeeklyNotes(@RequestBody WeeklyNotes weeklyNotes, @PathVariable Long id) {
        Optional<WeeklyNotes> updatedWeeklyNotes = Optional.ofNullable(weeklyNotesService.updateWeeklyNotes(weeklyNotes, id));
        if (updatedWeeklyNotes.isPresent()) {
            return new ResponseEntity<>(updatedWeeklyNotes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWeeklyNotesById(@PathVariable Long id) {
        try {
            weeklyNotesService.deleteWeeklyNotesById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/upload-file/{id}")
    public ResponseEntity<?> uploadFile(@RequestBody File file, @PathVariable Long id) {
        Optional<WeeklyNotes> updatedWeeklyNotes = Optional.ofNullable(weeklyNotesService.uploadFile(file, id));
        if (updatedWeeklyNotes.isPresent()) {
            return new ResponseEntity<>(updatedWeeklyNotes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/delete-file/{id}")
    public ResponseEntity<?> deleteFile(@RequestBody File file, @PathVariable Long id) {
        Optional<WeeklyNotes> updatedWeeklyNotes = Optional.ofNullable(weeklyNotesService.deleteFile(file, id));
        if (updatedWeeklyNotes.isPresent()) {
            return new ResponseEntity<>(updatedWeeklyNotes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
