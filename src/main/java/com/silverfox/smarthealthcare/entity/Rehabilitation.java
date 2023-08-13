package com.silverfox.smarthealthcare.entity;

import com.silverfox.smarthealthcare.enums.RehabilitationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rehabilitation")
public class Rehabilitation extends BaseTimeEntity{

    @Id
    @Column(name = "rehabilitation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "rehabilitation")
    private List<Biometric> biometrics = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RehabilitationStatus rehabilitationStatus;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private int goalTime;

    @Column
    private Integer actualTime;

    @Column
    private Integer remainingTime;

    @Column
    private Float travelRange;

    @Column
    private Float slope;

    @Column
    private Float speed;

    @Column
    private Float consumedCalories;

    @Column
    @Embedded
    BiometricAvg biometricAvg;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void addBiometric(Biometric biometric) {
        this.biometrics.add(biometric);
        biometric.setRehabilitation(this);
    }


    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setRehabilitationStatus(RehabilitationStatus rehabilitationStatus) {
        this.rehabilitationStatus = rehabilitationStatus;
    }

    public void setActualTime(Integer actualTime) {
        this.actualTime = actualTime;
    }

    public void setRemainingTime(Integer remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setTravelRange(Float travelRange) {
        this.travelRange = travelRange;
    }

    public void setSlope(Float slope) {
        this.slope = slope;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public void setConsumedCalories(Float consumedCalories) {
        this.consumedCalories = consumedCalories;
    }

    public void setBiometricAvg(BiometricAvg biometricAvg) {
        this.biometricAvg = biometricAvg;
    }
}
