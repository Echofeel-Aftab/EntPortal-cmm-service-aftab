package com.org.entportal.response.cashdepositapproval.processor;

import com.org.entportal.response.ApproveCollectionResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ApproveCollectionResponseProcessor {

    public ApproveCollectionResponse approveCollection(Map response) {
        ApproveCollectionResponse approveCollectionResponse = new ApproveCollectionResponse();

        String retText = (String) response.get("retText");
        String mcdelflg = (String) response.get("mcdelflg");
        List c1 = (ArrayList) response.get("c1");
        //List c2 = (ArrayList) response.get("c2");

        approveCollectionResponse.setC1(c1);
        // approveCollectionResponse.setC2(c2);
        approveCollectionResponse.setRetText(retText);
        approveCollectionResponse.setMcdelflg(mcdelflg);

        return approveCollectionResponse;
    }
}
