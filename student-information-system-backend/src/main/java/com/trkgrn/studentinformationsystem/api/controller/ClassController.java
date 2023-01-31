package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.entity.Class;
import com.trkgrn.studentinformationsystem.api.service.ClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/class")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClassById(@PathVariable Long id) {
        Optional<Class> classFromDb = Optional.ofNullable(classService.getClassById(id));
        if (classFromDb.isPresent()) {
            return new ResponseEntity<>(classFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllClasses() {
        return new ResponseEntity<>(classService.getAllClasses(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveClass(@RequestBody Class newClass){
        try {
            Class savedClass = classService.saveClass(newClass);
            return new ResponseEntity<>(savedClass, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClass(@RequestBody Class updatedClass, @PathVariable Long id){
        Optional<Class> classFromDb = Optional.ofNullable(classService.updateClass(updatedClass, id));
        if (classFromDb.isPresent()) {
            return new ResponseEntity<>(classFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassById(@PathVariable Long id){
        try {
            classService.deleteClassById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
