package com.trkgrn.studentinformationsystem.api.repository;

import com.trkgrn.studentinformationsystem.api.model.entity.LessonRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRequestRepository extends JpaRepository<LessonRequest, Long> {
}
