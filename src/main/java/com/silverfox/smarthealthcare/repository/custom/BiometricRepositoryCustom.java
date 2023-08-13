package com.silverfox.smarthealthcare.repository.custom;

import com.silverfox.smarthealthcare.entity.BiometricAvg;
import com.silverfox.smarthealthcare.entity.Rehabilitation;

public interface BiometricRepositoryCustom {

    BiometricAvg findBiometricAvg(Rehabilitation rehabilitation);

}
