package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.LiveLesson;
import com.trkgrn.studentinformationsystem.api.model.entity.Student;
import com.trkgrn.studentinformationsystem.api.model.entity.User;
import com.trkgrn.studentinformationsystem.api.repository.LiveLessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiveLessonService {

    private final LiveLessonRepository liveLessonRepository;

    public LiveLessonService(LiveLessonRepository liveLessonRepository) {
        this.liveLessonRepository = liveLessonRepository;
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

    public LiveLesson updateLiveLesson(LiveLesson liveLesson,Long id){
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

    public List<LiveLesson> getLiveLessonByTeacher_User_UserId(Long userId) {
        return liveLessonRepository.getLiveLessonByTeacher_User_UserId(userId).orElse(null);
    }

    public List<LiveLesson> getLiveLessonByStudent_User_UserId(Long userId) {
        return liveLessonRepository.getLiveLessonsByLessonRequest_Student_User_UserId(userId).orElse(null);
    }

    public List<LiveLesson> getAvailableLiveLessonsByStudent(Student student) {
        return liveLessonRepository.getAvailableLiveLessonsByStudentId(student.getStudentId()).orElse(null);
    }

}
