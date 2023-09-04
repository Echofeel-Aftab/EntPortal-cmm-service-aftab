package com.org.entportal.response.updcollamt.processor;

import com.org.entportal.response.OldCurrAmtResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OldCurrAmtResponseProcessor {
    public OldCurrAmtResponse processOldCurrAmtResponse(Map response) {
        OldCurrAmtResponse oldCurrAmtResponse = new OldCurrAmtResponse();
        String mcollamtold = (String) response.get("mncollamtold");
        oldCurrAmtResponse.setMcollamtold(mcollamtold);
        String mtotamtcurr = (String) response.get("mntotamtcurr");
        oldCurrAmtResponse.setMtotamtcurr(mtotamtcurr);
        return oldCurrAmtResponse;
    }
}
