package com.org.entportal.service;

import com.org.entportal.request.ChangeDepositSlipRequest;
import com.org.entportal.request.DepositGenerationSlipRequest;
import com.org.entportal.response.DepositGenerationSlipResponse;

import java.util.Optional;

public interface DepositService {

    public Object changeDepositSlip(ChangeDepositSlipRequest changeDepositSlipRequest);

    public Optional<DepositGenerationSlipResponse> depositGenerationSlip(DepositGenerationSlipRequest depositGenerationSlipRequest);
}
