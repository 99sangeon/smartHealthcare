package com.silverfox.smarthealthcare.repository.custom;

import com.silverfox.smarthealthcare.dto.RehabilitationResponse;
import com.silverfox.smarthealthcare.entity.Rehabilitation;

import java.util.List;

public interface RehabilitationRepositoryCustom {

    List<Rehabilitation> findRehabilitationList();

    Rehabilitation findRehabilitation(Long id);
}
