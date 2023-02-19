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
    private Integer midtermExam;
    private Integer finalExam;
    private Integer average;
    private LetterGrade letterGrade;
    private StudentDto student;
    private LiveLessonDto liveLesson;
}
