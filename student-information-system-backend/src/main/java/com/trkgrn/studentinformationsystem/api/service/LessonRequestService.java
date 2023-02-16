package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.LessonRequest;
import com.trkgrn.studentinformationsystem.api.repository.LessonRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonRequestService {

    private final LessonRequestRepository lessonRequestRepository;


    public LessonRequestService(LessonRequestRepository lessonRequestRepository) {
        this.lessonRequestRepository = lessonRequestRepository;
    }

    public LessonRequest saveLessonRequest(LessonRequest lessonRequest) {
        return lessonRequestRepository.save(lessonRequest);
    }

    public LessonRequest getLessonRequestById(Long id) {
        return lessonRequestRepository.findById(id).orElse(null);
    }

    public void deleteLessonRequestById(Long id) {
        lessonRequestRepository.deleteById(id);
    }

    public LessonRequest updateLessonRequest(LessonRequest lessonRequest, Long id) {
        Optional<LessonRequest> lessonRequestOptional = lessonRequestRepository.findById(id);

        if (lessonRequestOptional.isPresent()) {
            LessonRequest updatedLessonRequest = lessonRequestOptional.get();
            updatedLessonRequest.setLessonRequestId(id);
            updatedLessonRequest.setLiveLesson(lessonRequest.getLiveLesson());
            updatedLessonRequest.setStudent(lessonRequest.getStudent());
            updatedLessonRequest.setIsApproved(lessonRequest.getIsApproved());
            return lessonRequestRepository.save(updatedLessonRequest);
        }

        return null;
    }

    public Iterable<LessonRequest> getAllLessonRequests() {
        return lessonRequestRepository.findAll();
    }
}
