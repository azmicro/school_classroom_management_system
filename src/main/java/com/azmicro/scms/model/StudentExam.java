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
public class StudentExam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="examDate")
    private Date examDate;

    @Column(name="correctionDate")
    private Date correctionDate;

    @Column(name="mark")
    private double mark;

    @ManyToOne
    @JoinColumn(name="idStudent")
    private Student student;

    @ManyToOne
    @JoinColumn(name="idExam")
    private Exam exam;
}
