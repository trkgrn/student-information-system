package com.trkgrn.studentinformationsystem.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonRequestDto {
    private Long lessonRequestId;
    private LiveLessonDto liveLesson;
    private StudentDto student;
    private Boolean isApproved;
}
