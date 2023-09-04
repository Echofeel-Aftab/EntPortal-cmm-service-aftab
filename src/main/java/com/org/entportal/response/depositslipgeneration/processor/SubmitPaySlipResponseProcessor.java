package com.org.entportal.response.depositslipgeneration.processor;

import com.org.entportal.response.SubmitPaySlipResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SubmitPaySlipResponseProcessor {

    public SubmitPaySlipResponse submitPaySlip(Map response) {
        SubmitPaySlipResponse submitPaySlipResponse = new SubmitPaySlipResponse();

        String retText = (String) response.get("retText");

        submitPaySlipResponse.setRetText(retText);

        return submitPaySlipResponse;
    }
}
