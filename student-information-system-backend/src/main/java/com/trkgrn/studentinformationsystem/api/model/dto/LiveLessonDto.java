package com.trkgrn.studentinformationsystem.api.model.dto;

import com.trkgrn.studentinformationsystem.api.model.entity.Class;
import com.trkgrn.studentinformationsystem.api.model.entity.EducationSeason;
import com.trkgrn.studentinformationsystem.api.model.entity.Lesson;
import com.trkgrn.studentinformationsystem.api.model.entity.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LiveLessonDto {
    private Long liveLessonId;
    private Integer midtermPercent;
    private Integer finalPercent;
    private Boolean isActive;
    private Period period;
    private EducationSeason educationSeason;
    private Class _class;
    private Lesson lesson;
    private TeacherDto teacher;
}
