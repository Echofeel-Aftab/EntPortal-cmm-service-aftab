package com.org.entportal.response.bulksaveinv.processor;

import com.org.entportal.response.DoBulkSaveInvResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DoBulkSaveInvResponseProcessor {

    public DoBulkSaveInvResponse processDoBulkSaveInvResponse(Map response) {
        DoBulkSaveInvResponse doBulkSaveInvResponse = new DoBulkSaveInvResponse();

        String retText = (String) response.get("RETTEXT");

        doBulkSaveInvResponse.setRetText(retText);

        return doBulkSaveInvResponse;
    }


}
