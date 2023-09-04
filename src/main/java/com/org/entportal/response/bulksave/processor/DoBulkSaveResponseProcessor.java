package com.org.entportal.response.bulksave.processor;

import com.org.entportal.response.DoBulkSaveResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DoBulkSaveResponseProcessor {

    public DoBulkSaveResponse processDoBulkSaveResponse(Map response) {
        DoBulkSaveResponse doBulkSaveResponse = new DoBulkSaveResponse();

        String retText = (String) response.get("RETTEXT");

        doBulkSaveResponse.setRetText(retText);

        return doBulkSaveResponse;
    }

}
