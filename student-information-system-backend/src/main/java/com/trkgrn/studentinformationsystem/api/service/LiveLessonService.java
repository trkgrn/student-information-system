package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.dto.GradeDto;
import com.trkgrn.studentinformationsystem.api.model.dto.LiveLessonDto;
import com.trkgrn.studentinformationsystem.api.model.dto.NotesByLiveLessonDto;
import com.trkgrn.studentinformationsystem.api.model.entity.LiveLesson;
import com.trkgrn.studentinformationsystem.api.model.entity.Student;
import com.trkgrn.studentinformationsystem.api.repository.LiveLessonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LiveLessonService {

    private final LiveLessonRepository liveLessonRepository;
    private final ModelMapper modelMapper;

    public LiveLessonService(LiveLessonRepository liveLessonRepository, ModelMapper modelMapper) {
        this.liveLessonRepository = liveLessonRepository;
        this.modelMapper = modelMapper;
    }

    public LiveLesson saveLiveLesson(LiveLesson liveLesson) {
        return liveLessonRepository.save(liveLesson);
    }

    public LiveLesson getLiveLessonById(Long id) {
        return liveLessonRepository.findById(id).orElse(null);
    }

    public void deleteLiveLessonById(Long id) {
        liveLessonRepository.deleteById(id);
    }

    public LiveLesson updateLiveLesson(LiveLesson liveLesson, Long id) {
        Optional<LiveLesson> liveLessonOptional = liveLessonRepository.findById(id);
        if (liveLessonOptional.isPresent()) {
            LiveLesson updatedLiveLesson = liveLessonOptional.get();
            updatedLiveLesson.setLiveLessonId(id);
            updatedLiveLesson.setLesson(liveLesson.getLesson());
            updatedLiveLesson.setTeacher(liveLesson.getTeacher());
            updatedLiveLesson.set_class(liveLesson.get_class());
            updatedLiveLesson.setEducationSeason(liveLesson.getEducationSeason());
            updatedLiveLesson.setPeriod(liveLesson.getPeriod());
            updatedLiveLesson.setMidtermPercent(liveLesson.getMidtermPercent());
            updatedLiveLesson.setFinalPercent(liveLesson.getFinalPercent());
            updatedLiveLesson.setIsActive(liveLesson.getIsActive());
            return liveLessonRepository.save(updatedLiveLesson);
        }
        return null;
    }

    public List<LiveLesson> getAllLiveLessons() {
        return liveLessonRepository.findAll();
    }

    public List<LiveLesson> getLiveLessonByIsActiveIsTrue() {
        return liveLessonRepository.getLiveLessonByIsActiveIsTrue().orElse(null);
    }

    public Optional<List<LiveLesson>> getLiveLessonsByTeacher_TeacherId(Long teacherId) {
        return liveLessonRepository.getLiveLessonsByTeacher_TeacherId(teacherId);
    }

    public List<LiveLesson> getLiveLessonByStudent_StudentId(Long studentId) {
        return liveLessonRepository.getLiveLessonsByLessonRequest_Student_StudentIdAndLessonRequest_IsApproved(studentId,true).orElse(null);
    }

    public List<LiveLesson> getAvailableLiveLessonsByStudent(Student student) {
        return liveLessonRepository.getAvailableLiveLessonsByStudentId(student.getStudentId()).orElse(null);
    }

    public List<NotesByLiveLessonDto> getNotesByLiveLesson_Teacher_TeacherId(Long teacherId) {
        List<LiveLesson> liveLessons = liveLessonRepository.getLiveLessonsByTeacher_TeacherId(teacherId).orElse(null);
        List<NotesByLiveLessonDto> notes =liveLessons.
                stream()
                .map(liveLesson -> new NotesByLiveLessonDto(modelMapper.map(liveLesson, LiveLessonDto.class),
                        liveLesson.getNotes()
                                .stream()
                                .map(note -> modelMapper.map(note, GradeDto.class))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());


        return notes;
    }

}
