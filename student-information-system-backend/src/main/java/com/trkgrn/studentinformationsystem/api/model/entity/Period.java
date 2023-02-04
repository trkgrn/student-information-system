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
@Table(schema = "public", name = "period")
public class Period {
    @Id
    @Column(name = "period_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long periodId;

    @Column(name = "name")
    private String name;
}
