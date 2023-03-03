package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.LectureNote;
import com.trkgrn.studentinformationsystem.api.model.entity.WeeklyNotes;
import com.trkgrn.studentinformationsystem.api.repository.LectureNoteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class LectureNoteService {

    @Value("${education.total-week-per-period}")
    private Integer totalWeekPerPeriod;

    private final LectureNoteRepository lectureNoteRepository;
    private final WeeklyNotesService weeklyNotesService;

    public LectureNoteService(LectureNoteRepository lectureNoteRepository, WeeklyNotesService weeklyNotesService) {
        this.lectureNoteRepository = lectureNoteRepository;
        this.weeklyNotesService = weeklyNotesService;
    }

    public LectureNote saveLectureNote(LectureNote lectureNote) {

        LectureNote savedLectureNote = lectureNoteRepository.save(lectureNote);

        List<WeeklyNotes> weeklyNotes = new ArrayList<>();
        IntStream.range(1, totalWeekPerPeriod + 1).forEach(i -> {
            WeeklyNotes weeklyNote = new WeeklyNotes();
            weeklyNote.setWeekNumber(i);
            weeklyNote.setLectureNote(savedLectureNote);
            weeklyNotes.add(weeklyNote);
        });
        weeklyNotesService.saveWeeklyNotes(weeklyNotes);

        return savedLectureNote;
    }

    public LectureNote getLectureNoteById(Long id) {
        return lectureNoteRepository.findById(id).orElse(null);
    }

    public void deleteLectureNoteById(Long id) {
        lectureNoteRepository.deleteById(id);

    }

    public LectureNote updateLectureNote(LectureNote lectureNote, Long id){
        Optional<LectureNote> lectureNoteOptional = lectureNoteRepository.findById(id);

        if (lectureNoteOptional.isPresent()) {
            LectureNote updatedLectureNote = lectureNoteOptional.get();
            updatedLectureNote.setLectureNoteId(id);
            updatedLectureNote.setWeeklyNotes(lectureNote.getWeeklyNotes());
            updatedLectureNote.setLiveLesson(lectureNote.getLiveLesson());
            return lectureNoteRepository.save(updatedLectureNote);
        }

        return null;
    }

    public List<LectureNote> getAllLectureNotes() {
        return lectureNoteRepository.findAll();
    }
}
