package com.org.entportal.response.queryprintdepositslip.processor;

import com.org.entportal.response.FindSerialPaySlipResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FindSerialPaySlipResponseProcessor {

    public FindSerialPaySlipResponse findSerialPaySlip(Map response) {
        FindSerialPaySlipResponse findSerialPaySlipResponse = new FindSerialPaySlipResponse();

        String mcserialno = (String) response.get("mcserialno");

        findSerialPaySlipResponse.setMcserialno(mcserialno);

        return findSerialPaySlipResponse;
    }
}
