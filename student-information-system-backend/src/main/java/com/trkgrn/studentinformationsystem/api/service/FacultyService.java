package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.Faculty;
import com.trkgrn.studentinformationsystem.api.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty saveFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public void deleteFacultyById(Long id) {
        facultyRepository.deleteById(id);
    }

    public Faculty updateFaculty(Faculty faculty, Long id) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(id);

        if (facultyOptional.isPresent()) {
            Faculty updatedFaculty = facultyOptional.get();
            updatedFaculty.setFacultyId(id);
            updatedFaculty.setName(faculty.getName());
            return facultyRepository.save(updatedFaculty);
        }

        return null;
    }

    public Iterable<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }
}
