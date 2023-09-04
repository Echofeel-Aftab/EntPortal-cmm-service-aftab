package com.org.entportal.service;

import com.org.entportal.request.AddActionPaySlipRequest;
import com.org.entportal.request.GenerateDepositSlipRequest;
import com.org.entportal.request.SubmitPaySlipRequest;
import com.org.entportal.response.AddActionPaySlipResponse;
import com.org.entportal.response.GenerateDepositSlipResponse;
import com.org.entportal.response.SubmitPaySlipResponse;

import java.util.Optional;

public interface DepositSlipGenerationService {

    public Optional<GenerateDepositSlipResponse> generateDepositSlip(GenerateDepositSlipRequest generateDepositSlipRequest);

    public Optional<SubmitPaySlipResponse> submitPaySlip(SubmitPaySlipRequest submitPaySlipRequest);

    public Optional<AddActionPaySlipResponse> addActionPaySlip(AddActionPaySlipRequest addActionPaySlipRequest);
}
