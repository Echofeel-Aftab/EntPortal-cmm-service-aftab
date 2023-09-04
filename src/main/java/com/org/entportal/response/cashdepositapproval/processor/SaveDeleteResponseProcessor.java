package com.org.entportal.response.cashdepositapproval.processor;

import com.org.entportal.response.SaveDeleteResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SaveDeleteResponseProcessor {

    public SaveDeleteResponse saveDelete(Map response) {
        SaveDeleteResponse saveDeleteResponse = new SaveDeleteResponse();

        String retText = (String) response.get("retText");

        saveDeleteResponse.setRetText(retText);

        return saveDeleteResponse;
    }
}
