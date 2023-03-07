package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.File;
import com.trkgrn.studentinformationsystem.api.model.entity.WeeklyNotes;
import com.trkgrn.studentinformationsystem.api.repository.WeeklyNotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeeklyNotesService {

    private final WeeklyNotesRepository weeklyNotesRepository;

    public WeeklyNotesService(WeeklyNotesRepository weeklyNotesRepository) {
        this.weeklyNotesRepository = weeklyNotesRepository;
    }

    public WeeklyNotes saveWeeklyNotes(WeeklyNotes weeklyNotes) {
        return weeklyNotesRepository.save(weeklyNotes);
    }

    public List<WeeklyNotes> saveWeeklyNotes(List<WeeklyNotes> weeklyNotes) {
        return weeklyNotesRepository.saveAll(weeklyNotes);
    }

    public WeeklyNotes getWeeklyNotesById(Long id) {
        return weeklyNotesRepository.findById(id).orElse(null);
    }

    public void deleteWeeklyNotesById(Long id) {
        weeklyNotesRepository.deleteById(id);
    }

    public WeeklyNotes updateWeeklyNotes(WeeklyNotes weeklyNotes, Long id) {
        Optional<WeeklyNotes> weeklyNotesOptional = weeklyNotesRepository.findById(id);

        if (weeklyNotesOptional.isPresent()) {
            WeeklyNotes updatedWeeklyNotes = weeklyNotesOptional.get();
            updatedWeeklyNotes.setWeeklyNotesId(id);
            updatedWeeklyNotes.setLectureNote(weeklyNotes.getLectureNote());
            updatedWeeklyNotes.setFiles(weeklyNotes.getFiles());
            updatedWeeklyNotes.setDescription(weeklyNotes.getDescription());
            updatedWeeklyNotes.setWeekNumber(weeklyNotes.getWeekNumber());
            return weeklyNotesRepository.save(updatedWeeklyNotes);
        }

        return null;
    }

    public List<WeeklyNotes> getAllWeeklyNotes() {
        return weeklyNotesRepository.findAll();
    }

    public WeeklyNotes uploadFile(File file, Long weeklyNotesId){
        Optional<WeeklyNotes> weeklyNotesOptional = weeklyNotesRepository.findById(weeklyNotesId);
        if (weeklyNotesOptional.isPresent()) {
            WeeklyNotes updatedWeeklyNotes = weeklyNotesOptional.get();
            updatedWeeklyNotes.getFiles().add(file);
            return weeklyNotesRepository.save(updatedWeeklyNotes);
        }

       return null;
    }

    public WeeklyNotes deleteFile(File file, Long weeklyNotesId){
        Optional<WeeklyNotes> weeklyNotesOptional = weeklyNotesRepository.findById(weeklyNotesId);
        if (weeklyNotesOptional.isPresent()) {
            WeeklyNotes updatedWeeklyNotes = weeklyNotesOptional.get();
            updatedWeeklyNotes.getFiles().remove(file);
            return weeklyNotesRepository.save(updatedWeeklyNotes);
        }

       return null;
    }

    public WeeklyNotes updateDescription(String description, Long weeklyNotesId){
        Optional<WeeklyNotes> weeklyNotesOptional = weeklyNotesRepository.findById(weeklyNotesId);
        if (weeklyNotesOptional.isPresent()) {
            WeeklyNotes updatedWeeklyNotes = weeklyNotesOptional.get();
            updatedWeeklyNotes.setDescription(description);
            return weeklyNotesRepository.save(updatedWeeklyNotes);
        }

       return null;
    }


}
