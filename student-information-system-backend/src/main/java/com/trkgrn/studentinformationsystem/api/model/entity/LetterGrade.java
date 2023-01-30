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
@Table(schema = "public", name = "letter_grade")
public class LetterGrade {

    @Id
    @Column(name = "letter_grade_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long letterGradeId;

    @Column(name = "grade",nullable = false)
    private String grade;

    @Column(name = "min",nullable = false)
    private Integer min;

    @Column(name = "max",nullable = false)
    private Integer max;

    @Column(name = "coefficient",nullable = false)
    private Double coefficient;

    @Column(name = "is_pass")
    private Boolean isPass;
}
