package com.silverfox.smarthealthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "biometric")
public class Biometric extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "biometric_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rehabilitation_id")
    private Rehabilitation rehabilitation;

    @Column(nullable = false)
    private int bpm;

    @Column(nullable = false)
    private int temperature;

    @Column(nullable = false)
    private float oxygenSaturation;

    public void setRehabilitation(Rehabilitation rehabilitation) {
        this.rehabilitation = rehabilitation;
    }
}
