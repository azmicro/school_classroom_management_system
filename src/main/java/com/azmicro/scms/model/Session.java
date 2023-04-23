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
public class Session implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day")
    private String day;

    @Column(name = "hourStart")
    private String hourStart;

    @Column(name = "hourEnd")
    private String hourEnd;

    @ManyToOne
    @JoinColumn(name="idGrade")
    private Grade grade;
}
