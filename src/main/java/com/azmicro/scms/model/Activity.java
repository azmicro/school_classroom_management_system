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
public class Activity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="wording")
    private String wording;

    @ManyToOne
    @JoinColumn(name="idSemester")
    private Semester semester;
}
