package com.silverfox.smarthealthcare.service;

import com.silverfox.smarthealthcare.dto.*;

import java.util.List;

public interface RehabilitationService {

    List<RehabilitationSimpleResponse> getRehabilitationList();

    RehabilitationResponse getRehabilitation(Long id);

    Long saveRehabilitation(RehabilitationInitialRequest initialRequest);

    void rehabilitationStart(Long id);

    void rehabilitationEnd(Long id,  RehabilitationEndRequest endRequest);

    RehabilitationAvgResponse getRehabilitationAvg(Long id, int compareCnt);
}
