package com.silverfox.smarthealthcare.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.silverfox.smarthealthcare.entity.Rehabilitation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}
