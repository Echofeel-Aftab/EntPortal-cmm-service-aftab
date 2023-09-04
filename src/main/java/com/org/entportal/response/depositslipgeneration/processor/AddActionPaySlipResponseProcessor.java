package com.org.entportal.response.depositslipgeneration.processor;

import com.org.entportal.response.AddActionPaySlipResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AddActionPaySlipResponseProcessor {

    public AddActionPaySlipResponse addActionPaySlip(Map response) {
        AddActionPaySlipResponse addActionPaySlipResponse = new AddActionPaySlipResponse();

        String mccustname = (String) response.get("mccustname");
        List short1 = (ArrayList) response.get("short");
        List detail = (ArrayList) response.get("detail");
        List detail_temp = (ArrayList) response.get("detail_temp");
        List pcreasoncd = (ArrayList) response.get("pcreasoncd");

        addActionPaySlipResponse.setMccustname(mccustname);
        addActionPaySlipResponse.setDetail(detail);
        addActionPaySlipResponse.setDetail_temp(detail_temp);
        addActionPaySlipResponse.setPcreasoncd(pcreasoncd);
        addActionPaySlipResponse.setShort1(short1);

        return addActionPaySlipResponse;
    }
}
