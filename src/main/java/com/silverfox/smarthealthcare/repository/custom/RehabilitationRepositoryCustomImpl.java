package com.silverfox.smarthealthcare.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.silverfox.smarthealthcare.dto.BiometricAvgResponse;
import com.silverfox.smarthealthcare.dto.RehabilitationAvgResponse;
import com.silverfox.smarthealthcare.entity.Patient;
import com.silverfox.smarthealthcare.entity.Rehabilitation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.silverfox.smarthealthcare.entity.QBiometric.biometric;
import static com.silverfox.smarthealthcare.entity.QPatient.patient;
import static com.silverfox.smarthealthcare.entity.QRehabilitation.rehabilitation;

@Repository
@RequiredArgsConstructor
public class RehabilitationRepositoryCustomImpl implements RehabilitationRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Rehabilitation> findRehabilitationList() {

        List<Rehabilitation> rehabilitations = queryFactory
                .selectFrom(rehabilitation)
                .leftJoin(rehabilitation.patient, patient).fetchJoin()
                .orderBy(rehabilitation.id.desc())
                .fetch();

        return rehabilitations;
    }

    @Override
    public Rehabilitation findRehabilitation(Long id) {

        Rehabilitation r = queryFactory
                .selectFrom(rehabilitation)
                .distinct()
                .leftJoin(rehabilitation.patient, patient).fetchJoin()
                .leftJoin(rehabilitation.biometrics, biometric).fetchJoin()
                .where(rehabilitation.id.eq(id))
                .orderBy(biometric.createdDate.asc())
                .fetchOne();

        return r;
    }

    @Override
    public RehabilitationAvgResponse findRehabilitationAvg(Rehabilitation r, Patient patient, int compareCnt) {

        RehabilitationAvgResponse rehabilitationAvg = queryFactory
                .select(Projections.constructor(
                        RehabilitationAvgResponse.class,
                        Projections.constructor(
                                BiometricAvgResponse.class,
                                rehabilitation.biometricAvg.bpmAvg.avg().floatValue(),
                                rehabilitation.biometricAvg.temperatureAvg.avg().floatValue(),
                                rehabilitation.biometricAvg.oxygenSaturationAvg.avg().floatValue()
                        ),
                        rehabilitation.actualTime.avg().floatValue(),
                        rehabilitation.consumedCalories.avg().floatValue(),
                        rehabilitation.speed.avg().floatValue(),
                        rehabilitation.travelRange.avg().floatValue(),
                        rehabilitation.slope.avg().floatValue()
                ))
                .from(rehabilitation)
                .where(rehabilitation.in(
                        JPAExpressions.selectFrom(rehabilitation)
                                .where(rehabilitation.patient.eq(patient)
                                        .and(rehabilitation.id.lt(r.getId())))
                                .orderBy(rehabilitation.id.desc())
                                .limit(compareCnt)
                        )
                )
                .fetchOne();

        return rehabilitationAvg;
    }

}
