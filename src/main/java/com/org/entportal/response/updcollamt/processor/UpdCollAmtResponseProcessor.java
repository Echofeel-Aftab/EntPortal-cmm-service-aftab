package com.org.entportal.response.updcollamt.processor;

import com.org.entportal.response.UpdCollAmtResponse;
import com.org.entportal.response.UserAccessResponse;
import com.org.entportal.response.getShortResponse;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UpdCollAmtResponseProcessor {

    public UpdCollAmtResponse processUpdCollAmtResponse(Map response) {
        UpdCollAmtResponse updCollAmtResponse = new UpdCollAmtResponse();
        String retText = (String) response.get("RETTEXT");
        updCollAmtResponse.setRetText(retText);
        updCollAmtResponse.setPncolamt((BigDecimal) response.get("pncolamt"));
        updCollAmtResponse.setPoldncolamt((BigDecimal) response.get("poldncolamt"));
        updCollAmtResponse.setLv_cprodcode2((String) response.get("lv_cprodcode2"));
        updCollAmtResponse.setLv_cmode((String) response.get("lv_cmode"));
        updCollAmtResponse.setLn_colamt((BigDecimal) response.get("ln_colamt"));
        updCollAmtResponse.setLv_cstatcode((String) response.get("lv_cstatcode"));
        updCollAmtResponse.setRetText((String) response.get("RETTEXT"));
        return updCollAmtResponse;
    }
    
    public getShortResponse processgetShortResponse(Map response) {
    	getShortResponse getshortResponse = new getShortResponse();
        List cur = (ArrayList) response.get("SHORTDETAIL");
        getshortResponse.setSHORTDETAIL(cur);
        return getshortResponse;
    }
}
