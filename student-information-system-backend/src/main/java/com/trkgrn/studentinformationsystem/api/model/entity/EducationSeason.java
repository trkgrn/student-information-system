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
@Table(schema = "public", name = "education_season")
public class EducationSeason {

    @Id
    @Column(name = "education_season_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long educationSeasonId;

    @Column(name = "name")
    private String name;
}
