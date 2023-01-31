package com.trkgrn.studentinformationsystem.api.repository;

import com.trkgrn.studentinformationsystem.api.model.entity.LiveLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveLessonRepository extends JpaRepository<LiveLesson,Long> {
}
