package com.azmicro.scms.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wording")
    private String wording;

    @Column(name = "numberOfStudent")
    private int numberOfStudent;

    @OneToMany(mappedBy = "aclass")
    private List<Student> students;

    @OneToMany(mappedBy = "aclass")
    private List<Course> courses;

    @ManyToOne
    @JoinColumn(name = "idHighSchool")
    private HighSchool highSchool;

    @ManyToOne
    @JoinColumn(name = "idSchoolYear")
    private SchoolYear schoolYear;


}
