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
public class HighSchool implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nameAr")
    private String nameAr;

    @Column(name="nameFr")
    private String nameFr;

    @Column(name="adress")
    private String adress;

    @OneToMany(mappedBy = "highSchool")
    private List<Grade> grades;

}
