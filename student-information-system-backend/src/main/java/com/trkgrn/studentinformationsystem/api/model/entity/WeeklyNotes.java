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
@Table(schema = "public", name = "weekly_notes")
public class WeeklyNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weekly_notes_id")
    private Long weeklyNotesId;

    @ManyToOne
    @JoinColumn(name = "lecture_note_id")
    private LectureNote lectureNote;

    @Column(name = "week_number")
    private Integer weekNumber;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @CollectionTable(name = "file", joinColumns = @JoinColumn(name = "weekly_notes_id"))
    private List<File> files;


}
