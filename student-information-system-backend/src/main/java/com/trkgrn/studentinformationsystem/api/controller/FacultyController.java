package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.entity.Faculty;
import com.trkgrn.studentinformationsystem.api.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFacultyById(@PathVariable Long id) {
        Optional<Faculty> facultyFromDb = Optional.ofNullable(facultyService.getFacultyById(id));
        if (facultyFromDb.isPresent()) {
            return new ResponseEntity<>(facultyFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllFaculties() {
        return new ResponseEntity<>(facultyService.getAllFaculties(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveFaculty(@RequestBody Faculty faculty) {
        try {
            Faculty savedFaculty = facultyService.saveFaculty(faculty);
            return new ResponseEntity<>(savedFaculty, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFaculty(@RequestBody Faculty faculty, @PathVariable Long id) {
        Optional<Faculty> updatedFaculty = Optional.ofNullable(facultyService.updateFaculty(faculty, id));
        if (updatedFaculty.isPresent()) {
            return new ResponseEntity<>(updatedFaculty, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFacultyById(@PathVariable Long id) {
        try {
            facultyService.deleteFacultyById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}