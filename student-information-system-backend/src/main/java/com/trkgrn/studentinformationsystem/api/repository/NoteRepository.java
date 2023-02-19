package com.trkgrn.studentinformationsystem.api.repository;

import com.trkgrn.studentinformationsystem.api.model.entity.Note;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    Optional<List<Note>> getNotesByStudent_StudentIdAndLiveLesson_IsActiveIsTrue(Long studentId);
    Optional<List<Note>> getNotesByStudent_StudentIdAndLiveLesson_EducationSeason_EducationSeasonId(Long studentId, Long educationSeasonId);
    Optional<List<Note>> getNotesByStudent_StudentIdAndLiveLesson_EducationSeason_EducationSeasonIdAndLiveLesson_Period_PeriodId(Long studentId, Long educationSeasonId, Long periodId);
}
