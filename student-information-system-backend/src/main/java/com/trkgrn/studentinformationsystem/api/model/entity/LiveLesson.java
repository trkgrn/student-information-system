package com.trkgrn.studentinformationsystem.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "live_lesson")
public class LiveLesson {

    @Id
    @Column(name = "live_lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long liveLessonId;

    @Column(name = "midterm_percent")
    private Integer midtermPercent;

    @Column(name = "final_percent")
    private Integer finalPercent;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "period_id", referencedColumnName = "period_id")
    private Period period;

    @OneToOne
    @JoinColumn(name = "education_season_id", referencedColumnName = "education_season_id")
    private EducationSeason educationSeason;

    @OneToOne
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    private Class _class;

    @OneToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id")
    private Lesson lesson;

    @OneToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    private Teacher teacher;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "liveLesson")
    private List<LessonRequest> lessonRequest;
}
