package com.org.entportal.response.queryprintdepositslip.processor;

import com.org.entportal.response.PrintPaySlipResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PrintPaySlipResponseProcessor {

    public PrintPaySlipResponse printPaySlip(Map response) {
        PrintPaySlipResponse printPaySlipResponse = new PrintPaySlipResponse();

        List payslip = (ArrayList) response.get("payslip");
        List detail = (ArrayList) response.get("detail");

        printPaySlipResponse.setDetail(detail);
        printPaySlipResponse.setPayslip(payslip);

        return printPaySlipResponse;
    }
}
