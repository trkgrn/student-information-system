package com.trkgrn.studentinformationsystem.api.repository;

import com.trkgrn.studentinformationsystem.api.model.dto.SemesterDto;
import com.trkgrn.studentinformationsystem.api.model.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    Optional<List<Note>> getNotesByStudent_StudentIdAndLiveLesson_IsActiveIsTrue(Long studentId);
    Optional<List<Note>> getNotesByStudent_StudentIdAndLiveLesson_EducationSeason_EducationSeasonId(Long studentId, Long educationSeasonId);
    Optional<List<Note>> getNotesByStudent_StudentIdAndLiveLesson_EducationSeason_EducationSeasonIdAndLiveLesson_Period_PeriodId(Long studentId, Long educationSeasonId, Long periodId);

    @Query(value = "SELECT distinct new com.trkgrn.studentinformationsystem.api.model.dto.SemesterDto(e,p) " +
            "FROM Note n,LiveLesson l, Student s,Period p,EducationSeason e " +
            "WHERE n.liveLesson.liveLessonId = l.liveLessonId " +
            "AND n.student.studentId = s.studentId " +
            "AND l.period.periodId = p.periodId " +
            "AND l.educationSeason.educationSeasonId = e.educationSeasonId " +
            "AND s.studentId = :#{#studentId}")
    Optional<List<SemesterDto>> getSemestersByStudent_StudentId(Long studentId);

}
