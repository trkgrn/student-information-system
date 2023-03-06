package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.dto.LectureNoteDto;
import com.trkgrn.studentinformationsystem.api.model.dto.LiveLessonDto;
import com.trkgrn.studentinformationsystem.api.model.dto.WeeklyNotesDto;
import com.trkgrn.studentinformationsystem.api.model.entity.LectureNote;
import com.trkgrn.studentinformationsystem.api.model.entity.WeeklyNotes;
import com.trkgrn.studentinformationsystem.api.repository.LectureNoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class LectureNoteService {

    @Value("${education.total-week-per-period}")
    private Integer totalWeekPerPeriod;

    private final LectureNoteRepository lectureNoteRepository;
    private final WeeklyNotesService weeklyNotesService;
    private final ModelMapper modelMapper;

    public LectureNoteService(LectureNoteRepository lectureNoteRepository, WeeklyNotesService weeklyNotesService, ModelMapper modelMapper) {
        this.lectureNoteRepository = lectureNoteRepository;
        this.weeklyNotesService = weeklyNotesService;
        this.modelMapper = modelMapper;
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

    public LectureNote updateLectureNote(LectureNote lectureNote, Long id) {
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

    public LectureNoteDto getLectureNoteByLiveLessonId(Long liveLessonId) {
        LectureNote lectureNote = lectureNoteRepository.getLectureNoteByLiveLesson_LiveLessonId(liveLessonId);
        if (lectureNote != null) {
            LectureNoteDto lectureNoteDto = new LectureNoteDto(lectureNote.getLectureNoteId(),
                    modelMapper.map(lectureNote.getLiveLesson(), LiveLessonDto.class),
                    lectureNote.getWeeklyNotes()
                    .stream()
                    .map(weeklyNotes -> modelMapper.map(weeklyNotes, WeeklyNotesDto.class))
                    .collect(Collectors.toList()));
            return lectureNoteDto;
        }

        return null;
    }


}
