package com.azmicro.scms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class SchoolYear implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wording")
    private String wording;

    @Column(name="startDate")
    private Date startDate;

    @Column(name="endDate")
    private Date endDate;

    @OneToMany(mappedBy = "schoolYear")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Semester> semesters;

    @OneToMany(mappedBy = "schoolYear")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Grade> grades;
}
