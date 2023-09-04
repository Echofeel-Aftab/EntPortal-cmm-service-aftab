package com.org.entportal.response.addreasoninv.processor;

import com.org.entportal.response.AddReasonInvBulkEntResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AddReasonInvBulkEntResponseProcessor {

    public AddReasonInvBulkEntResponse processAddReasonInvBulkEntResponse(Map response) {
        AddReasonInvBulkEntResponse addReasonInvBulkEntResponse = new AddReasonInvBulkEntResponse();

        List mcreasoncd = (List) response.get("MCREASONCD");
        List shorts = (List) response.get("SHORT");
        String retText = (String) response.get("RETTEXT");

        addReasonInvBulkEntResponse.setRetText(retText);
        addReasonInvBulkEntResponse.setMcreasoncd(mcreasoncd);
        addReasonInvBulkEntResponse.setShorts(shorts);

        return addReasonInvBulkEntResponse;
    }
}
