package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.EducationSeason;
import com.trkgrn.studentinformationsystem.api.repository.EducationSeasonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EducationSeasonService {

    private final EducationSeasonRepository educationSeasonRepository;

    public EducationSeasonService(EducationSeasonRepository educationSeasonRepository) {
        this.educationSeasonRepository = educationSeasonRepository;
    }

    public EducationSeason saveEducationSeason(EducationSeason educationSeason) {
        return educationSeasonRepository.save(educationSeason);
    }

    public EducationSeason getEducationSeasonById(Long id) {
        return educationSeasonRepository.findById(id).orElse(null);
    }

    public void deleteEducationSeasonById(Long id) {
        educationSeasonRepository.deleteById(id);
    }

    public EducationSeason updateEducationSeason(EducationSeason educationSeason, Long id) {
        Optional<EducationSeason> educationSeasonOptional = educationSeasonRepository.findById(id);

        if (educationSeasonOptional.isPresent()) {
            EducationSeason updatedEducationSeason = educationSeasonOptional.get();
            updatedEducationSeason.setEducationSeasonId(id);
            updatedEducationSeason.setName(educationSeason.getName());
            return educationSeasonRepository.save(updatedEducationSeason);
        }

        return null;
    }

    public Iterable<EducationSeason> getAllEducationSeasons() {
        return educationSeasonRepository.findAll();
    }
}
