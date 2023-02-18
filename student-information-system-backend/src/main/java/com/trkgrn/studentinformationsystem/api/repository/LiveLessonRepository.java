package com.trkgrn.studentinformationsystem.api.repository;

import com.trkgrn.studentinformationsystem.api.model.entity.LiveLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LiveLessonRepository extends JpaRepository<LiveLesson, Long> {
    Optional<List<LiveLesson>> getLiveLessonByTeacher_User_UserId(Long userId);

    Optional<List<LiveLesson>> getLiveLessonsByLessonRequest_Student_User_UserId(Long userId);

    Optional<List<LiveLesson>> getLiveLessonByIsActiveIsTrue();


    @Query(value = "SELECT * FROM live_lesson" +
            "         WHERE is_active IS true" +
            "           AND live_lesson_id NOT IN (SELECT live_lesson_id" +
            "            FROM lesson_request" +
            "            WHERE is_approved IS NOT false AND student_id = :#{#studentId})",
            nativeQuery = true)
    Optional<List<LiveLesson>> getAvailableLiveLessonsByStudentId(Long studentId);


}
