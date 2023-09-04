package com.org.entportal.response.depositslipgeneration.processor;

import com.org.entportal.response.GenerateDepositSlipResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GenerateDepositSlipResponseProcessor {
    public GenerateDepositSlipResponse generateDepositSlip(Map response) {
        GenerateDepositSlipResponse generateDepositSlipResponse = new GenerateDepositSlipResponse();
        List dropdown_pcsubtype = (ArrayList) response.get("dropdown_pcsubtype");
        List dropdown_pcbankcddsp = (ArrayList) response.get("dropdown_pcbankcddsp");
        List cur_gendpo2 = (ArrayList) response.get("cur_gendpo2");
        List cur_gendpo1 = (ArrayList) response.get("cur_gendpo1");
        List cur_gendpo = (ArrayList) response.get("cur_gendpo");
        String mcbranch = (String) response.get("mcbranch");
        String mcarea = (String) response.get("mcarea");

        generateDepositSlipResponse.setCur_gendpo(cur_gendpo);
        generateDepositSlipResponse.setCur_gendpo1(cur_gendpo1);
        generateDepositSlipResponse.setCur_gendpo2(cur_gendpo2);
        generateDepositSlipResponse.setDropdown_pcbankcddsp(dropdown_pcbankcddsp);
        generateDepositSlipResponse.setDropdown_pcsubtype(dropdown_pcsubtype);
        generateDepositSlipResponse.setMcbranch(mcbranch);
        generateDepositSlipResponse.setMcarea(mcarea);
        return generateDepositSlipResponse;
    }
}
