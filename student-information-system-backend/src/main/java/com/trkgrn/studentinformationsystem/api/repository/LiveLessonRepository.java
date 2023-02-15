package com.trkgrn.studentinformationsystem.api.repository;

import com.trkgrn.studentinformationsystem.api.model.entity.LiveLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LiveLessonRepository extends JpaRepository<LiveLesson, Long> {
    Optional<List<LiveLesson>> getLiveLessonByTeacher_User_UserId(Long userId);
}
