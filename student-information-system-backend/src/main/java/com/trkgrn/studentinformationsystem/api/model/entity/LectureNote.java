package com.trkgrn.studentinformationsystem.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(schema = "public", name = "lecture_note")
@JsonIgnoreProperties({"weeklyNotes"})
public class LectureNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_note_id")
    private Long lectureNoteId;

    @OneToOne
    @JoinColumn(name = "live_lesson_id")
    private LiveLesson liveLesson;

    @OneToMany(mappedBy = "lectureNote", targetEntity = WeeklyNotes.class)
    private List<WeeklyNotes> weeklyNotes;

}
