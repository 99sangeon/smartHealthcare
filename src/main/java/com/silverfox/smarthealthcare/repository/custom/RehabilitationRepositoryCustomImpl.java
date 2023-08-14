package com.silverfox.smarthealthcare.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.silverfox.smarthealthcare.dto.BiometricAvgResponse;
import com.silverfox.smarthealthcare.dto.RehabilitationAvgResponse;
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
    public Optional<RehabilitationAvgResponse> findRehabilitationAvg(Rehabilitation rehab, int compareCnt) {

        Optional<RehabilitationAvgResponse> rehabilitationAvg = Optional.ofNullable(queryFactory
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
                        rehabilitation.goalTime.avg().floatValue()
                ))
                .from(rehabilitation)
                .where(rehabilitation.eq(rehab))
                .orderBy(rehabilitation.id.desc())
                .offset(1)
                .limit(compareCnt)
                .fetchOne());
        return rehabilitationAvg;
    }

}
