package com.silverfox.smarthealthcare.repository.custom;

import com.silverfox.smarthealthcare.dto.RehabilitationAvgResponse;
import com.silverfox.smarthealthcare.dto.RehabilitationResponse;
import com.silverfox.smarthealthcare.entity.Rehabilitation;

import java.util.List;
import java.util.Optional;

public interface RehabilitationRepositoryCustom {

    List<Rehabilitation> findRehabilitationList();

    Rehabilitation findRehabilitation(Long id);

    Optional<RehabilitationAvgResponse> findRehabilitationAvg(Rehabilitation rehabilitation, int compareCnt);
}
