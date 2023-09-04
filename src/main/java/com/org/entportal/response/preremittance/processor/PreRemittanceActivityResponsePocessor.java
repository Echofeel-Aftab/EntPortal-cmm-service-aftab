package com.org.entportal.response.preremittance.processor;

import com.org.entportal.response.PreRemittanceActivityResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PreRemittanceActivityResponsePocessor {

    public PreRemittanceActivityResponse processPreRemittanceActivityResponse(Map response) {

        PreRemittanceActivityResponse preRemittanceActivityResponse = new PreRemittanceActivityResponse();

        List c1 = (ArrayList) response.get("C1");
        List c2 = (ArrayList) response.get("C2");
        List curErr = (ArrayList) response.get("CUR_ERR");
        

        String psuccess  = (String) response.get("psuccess");
        BigDecimal ln_count1  = (BigDecimal) response.get("ln_count1");
        BigDecimal ln_count2  = (BigDecimal) response.get("ln_count2");
        String retVal  = (String) response.get("retVal");

        // retText
        preRemittanceActivityResponse.setC1(c1);
        preRemittanceActivityResponse.setC2(c2);
        preRemittanceActivityResponse.setCurErr(curErr);
        preRemittanceActivityResponse.setPsuccess(psuccess);
        preRemittanceActivityResponse.setLn_count1(ln_count1);
        preRemittanceActivityResponse.setLn_count2(ln_count2);
        preRemittanceActivityResponse.setRetVal(retVal);
        return preRemittanceActivityResponse;

    }

}
