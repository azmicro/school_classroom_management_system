package com.azmicro.scms.model;

import com.azmicro.scms.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String  code;
    @Column(name = "lastnamefr")
    private String lastNameFr;

    @Column(name = "lastnamear")
    private String lastNameAr;

    @Column(name = "firstnamefr")
    private String firstNameFr;

    @Column(name = "firstnamear")
    private String firstNameAr;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "dateOfBirth")
    private Date    dateOfBirth;

    @Column(name = "photo")
    private String  photo;

    @OneToMany
    @JoinColumn(name = "student")
    private List<Absence> absences;

    @OneToMany
    @JoinColumn(name = "student")
    private List<StudentActivity> studentActivities;

    @OneToMany
    @JoinColumn(name = "student")
    private List<StudentExam> studentExams;

   @ManyToOne
    @JoinColumn(name = "idClass")
    private Class aclass;







}
