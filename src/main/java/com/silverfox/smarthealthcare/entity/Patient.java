package com.silverfox.smarthealthcare.entity;

import com.silverfox.smarthealthcare.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long id;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "patient")
    private List<Rehabilitation> rehabilitations = new ArrayList<>();

    @Column(length = 20, nullable = false)
    private String name;

    @Column
    private int age;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(length = 6, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private float height;

    @Column
    private float weight;

    @Column
    private String mobile;

    public void addRehabilitation(Rehabilitation rehabilitation) {
        this.rehabilitations.add(rehabilitation);
        rehabilitation.setPatient(this);
    }

}