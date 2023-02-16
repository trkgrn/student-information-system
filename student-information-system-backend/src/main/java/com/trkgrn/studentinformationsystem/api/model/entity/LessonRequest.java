package com.trkgrn.studentinformationsystem.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "lesson_request")
public class LessonRequest {

    @Id
    @Column(name = "lesson_request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonRequestId;

    @OneToOne
    @JoinColumn(name = "live_lesson_id")
    private LiveLesson liveLesson;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "is_approved")
    private Boolean isApproved;

}
