package com.azmicro.scms.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Semester implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wording")
    private String wording;
    @Column(name="startDate")
    private Date startDate;

    @Column(name="endDate")
    private Date endDate;

    @OneToMany(mappedBy ="semester")
    private List<Exam> exams;

    @OneToMany(mappedBy ="semester")
    private List<Absence> absences;

    @OneToMany(mappedBy ="semester")
    private List<Activity> activities;

    @ManyToOne
    @JoinColumn(name = "idSchoolYear")
    private SchoolYear schoolYear;
}
