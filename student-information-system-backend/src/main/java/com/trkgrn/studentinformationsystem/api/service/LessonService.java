package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.Lesson;
import com.trkgrn.studentinformationsystem.api.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    public void deleteLessonById(Long id) {
        lessonRepository.deleteById(id);
    }

    public Lesson updateLesson(Lesson lesson, Long id) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);

        if (lessonOptional.isPresent()) {
            Lesson updatedLesson = lessonOptional.get();
            updatedLesson.setLessonId(id);
            updatedLesson.setName(lesson.getName());
            return lessonRepository.save(updatedLesson);
        }

        return null;
    }

    public Iterable<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }
}
