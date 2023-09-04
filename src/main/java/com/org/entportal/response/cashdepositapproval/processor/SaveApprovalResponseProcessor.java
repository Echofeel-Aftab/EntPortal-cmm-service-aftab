package com.org.entportal.response.cashdepositapproval.processor;

import com.org.entportal.response.SaveApprovalResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SaveApprovalResponseProcessor {

    public SaveApprovalResponse saveApproval(Map response) {
        SaveApprovalResponse saveApprovalResponse = new SaveApprovalResponse();

        String retText = (String) response.get("retText");

        saveApprovalResponse.setRetText(retText);

        return saveApprovalResponse;
    }
}
