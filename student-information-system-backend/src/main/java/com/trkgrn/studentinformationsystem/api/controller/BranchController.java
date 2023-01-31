package com.trkgrn.studentinformationsystem.api.controller;

import com.trkgrn.studentinformationsystem.api.model.entity.Branch;
import com.trkgrn.studentinformationsystem.api.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/branch")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBranchById(@PathVariable Long id) {
        Optional<Branch> branchFromDb = Optional.ofNullable(branchService.getBranchById(id));
        if (branchFromDb.isPresent()) {
            return new ResponseEntity<>(branchFromDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBranches() {
        return new ResponseEntity<>(branchService.getAllBranches(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveBranch(@RequestBody Branch branch) {
        try {
            Branch savedBranch = branchService.saveBranch(branch);
            return new ResponseEntity<>(savedBranch, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBranch(@RequestBody Branch branch, @PathVariable Long id) {
        Optional<Branch> updatedBranch = Optional.ofNullable(branchService.updateBranch(branch, id));
        if (updatedBranch.isPresent()) {
            return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBranchById(@PathVariable Long id) {
        try {
            branchService.deleteBranchById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
