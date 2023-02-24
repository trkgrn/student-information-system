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
@Table(schema = "public", name = "note")
public class Note {

    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;

    @Column(name = "state",nullable = false)
    private String state;

    @Column(name = "midterm_exam",nullable = true)
    private Integer midtermExam;

    @Column(name = "final_exam",nullable = true)
    private Integer finalExam;

    @Column(name = "average",nullable = true)
    private Integer average;

    @OneToOne
    @JoinColumn(name = "letter_grade_id",referencedColumnName = "letter_grade_id")
    private LetterGrade letterGrade;

    @OneToOne
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "live_lesson_id",referencedColumnName = "live_lesson_id")
    private LiveLesson liveLesson;
}
