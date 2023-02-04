package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.entity.Period;
import com.trkgrn.studentinformationsystem.api.service.PeriodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/period")
public class PeriodController {
    private final PeriodService periodService;

    public PeriodController(PeriodService periodService) {
        this.periodService = periodService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPeriods() {
        return new ResponseEntity<>(periodService.getAllPeriods(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPeriodById(@PathVariable Long id) {
        Optional<Period> periodFromDb = Optional.ofNullable(periodService.getPeriodById(id));
        if (periodFromDb.isPresent()) {
            return new ResponseEntity<>(periodFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> savePeriod(@RequestBody Period period) {
        try {
            Period savedPeriod = periodService.savePeriod(period);
            return new ResponseEntity<>(savedPeriod, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePeriod(@RequestBody Period period, @PathVariable Long id) {
        Optional<Period> updatedPeriod = Optional.ofNullable(periodService.updatePeriod(period, id));
        if (updatedPeriod.isPresent()) {
            return new ResponseEntity<>(updatedPeriod, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePeriodById(@PathVariable Long id) {
        try {
            periodService.deletePeriodById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
