package com.azmicro.scms.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Absence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "dateOfAbsence")
    private String dateOfAbsence;

    @ManyToOne
    @JoinColumn(name = "idSemester")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "idStudent")
    private Student student;


}
