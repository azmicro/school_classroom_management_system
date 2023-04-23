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
public class StudentActivity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dateOfActivity")
    private Date dateOfActivity;

    @Column(name="mark")
    private double mark;

    @ManyToOne
    @JoinColumn(name="idStudent")
    private Student student;

    @ManyToOne
    @JoinColumn(name="idActivity")
    private Activity activity;
}
