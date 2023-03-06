package com.trkgrn.studentinformationsystem.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LectureNoteDto {

    private Long lectureNoteId;
    private LiveLessonDto liveLesson;
    private List<WeeklyNotesDto> weeklyNotes;

}
