package com.trkgrn.studentinformationsystem.api.model.dto;

import com.trkgrn.studentinformationsystem.api.model.entity.LetterGrade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteDto {
    private Long noteId;
    private String state;
    private Double midtermExam;
    private Double finalExam;
    private Double average;
    private LetterGrade letterGrade;
    private StudentDto student;
    private LiveLessonDto liveLesson;
}
