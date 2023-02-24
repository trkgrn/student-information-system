package com.trkgrn.studentinformationsystem.api.repository;

import com.trkgrn.studentinformationsystem.api.model.entity.LessonRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRequestRepository extends JpaRepository<LessonRequest, Long> {

    Optional<List<LessonRequest>> getLessonRequestByStudent_StudentId(Long studentId);
    Optional<List<LessonRequest>> getLessonRequestsByIsApprovedIsNull();
}
