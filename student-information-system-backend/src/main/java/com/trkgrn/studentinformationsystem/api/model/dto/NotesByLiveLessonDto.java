package com.trkgrn.studentinformationsystem.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotesByLiveLessonDto {
    private LiveLessonDto liveLesson;
    private List<GradeDto> notes;
}
