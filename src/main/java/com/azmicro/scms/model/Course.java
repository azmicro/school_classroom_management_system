package com.azmicro.scms.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dateOfCourse")
    private Date dateOfCourse;

    @Column(name="courseContent")
    private String courseContent;

    @Column(name="courseDescription")
    private String courseDescription;

    @ManyToOne
    @JoinColumn(name="idGrade")
    private Grade grade;
}
