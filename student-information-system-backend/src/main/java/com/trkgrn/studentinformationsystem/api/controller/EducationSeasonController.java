package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.entity.EducationSeason;
import com.trkgrn.studentinformationsystem.api.service.EducationSeasonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/education-season")
public class EducationSeasonController {

    private final EducationSeasonService educationSeasonService;

    public EducationSeasonController(EducationSeasonService educationSeasonService) {
        this.educationSeasonService = educationSeasonService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEducationSeasonById(@PathVariable Long id) {
        Optional<EducationSeason> educationSeasonFromDb = Optional.ofNullable(educationSeasonService.getEducationSeasonById(id));
        if (educationSeasonFromDb.isPresent()) {
            return new ResponseEntity<>(educationSeasonFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEducationSeasons() {
        return new ResponseEntity<>(educationSeasonService.getAllEducationSeasons(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveEducationSeason(@RequestBody EducationSeason educationSeason) {
        try {
            EducationSeason savedEducationSeason = educationSeasonService.saveEducationSeason(educationSeason);
            return new ResponseEntity<>(savedEducationSeason, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEducationSeason(@RequestBody EducationSeason educationSeason, @PathVariable Long id) {
        Optional<EducationSeason> updatedEducationSeason = Optional.ofNullable(educationSeasonService.updateEducationSeason(educationSeason, id));
        if (updatedEducationSeason.isPresent()) {
            return new ResponseEntity<>(updatedEducationSeason, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEducationSeasonById(@PathVariable Long id) {
        try {
            educationSeasonService.deleteEducationSeasonById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}
