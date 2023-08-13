package com.silverfox.smarthealthcare.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.silverfox.smarthealthcare.entity.BiometricAvg;
import com.silverfox.smarthealthcare.entity.Rehabilitation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.silverfox.smarthealthcare.entity.QBiometric.biometric;

@Repository
@RequiredArgsConstructor
public class BiometricRepositoryCustomImpl implements BiometricRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public BiometricAvg findBiometricAvg(Rehabilitation rehabilitation) {
        BiometricAvg biometricAvg = queryFactory
                .select(Projections.constructor(
                        BiometricAvg.class,
                        biometric.bpm.avg().floatValue(),
                        biometric.temperature.avg().floatValue(),
                        biometric.oxygenSaturation.avg().floatValue()
                        ))
                .from(biometric)
                .where(biometric.rehabilitation.eq(rehabilitation))
                .fetchOne();

        return biometricAvg;
    }
}
