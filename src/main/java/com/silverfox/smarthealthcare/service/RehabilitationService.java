package com.silverfox.smarthealthcare.service;

import com.silverfox.smarthealthcare.dto.RehabilitationEndRequest;
import com.silverfox.smarthealthcare.dto.RehabilitationInitialRequest;
import com.silverfox.smarthealthcare.dto.RehabilitationResponse;
import com.silverfox.smarthealthcare.dto.RehabilitationSimpleResponse;

import java.util.List;

public interface RehabilitationService {

    List<RehabilitationSimpleResponse> getRehabilitationList();

    RehabilitationResponse getRehabilitation(Long id);

    Long saveRehabilitation(RehabilitationInitialRequest initialRequest);

    void rehabilitationStart(Long id);

    void rehabilitationEnd(Long id,  RehabilitationEndRequest endRequest);

}
