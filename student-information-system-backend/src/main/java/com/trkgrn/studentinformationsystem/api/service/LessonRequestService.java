package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.LessonRequest;
import com.trkgrn.studentinformationsystem.api.repository.LessonRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<LessonRequest> getAllLessonRequests() {
        return lessonRequestRepository.findAll();
    }

    public List<LessonRequest> getLessonRequestByStudent_StudentId(Long studentId) {
        return lessonRequestRepository.getLessonRequestByStudent_StudentId(studentId).orElse(null);
    }

    public List<LessonRequest> getLessonRequestsByIsApprovedIsNull() {
        return lessonRequestRepository.getLessonRequestsByIsApprovedIsNull().orElse(null);
    }

    public LessonRequest approveLessonRequest(Long id) {
        Optional<LessonRequest> lessonRequestOptional = lessonRequestRepository.findById(id);

        if (lessonRequestOptional.isPresent()) {
            LessonRequest updatedLessonRequest = lessonRequestOptional.get();
            updatedLessonRequest.setIsApproved(true);
            return lessonRequestRepository.save(updatedLessonRequest);
        }
        return null;
    }

    public LessonRequest rejectLessonRequest(Long id){
        Optional<LessonRequest> lessonRequestOptional = lessonRequestRepository.findById(id);

        if (lessonRequestOptional.isPresent()) {
            LessonRequest updatedLessonRequest = lessonRequestOptional.get();
            updatedLessonRequest.setIsApproved(false);
            return lessonRequestRepository.save(updatedLessonRequest);
        }
        return null;
    }
}
