package com.org.entportal.response.multiareauser.processor;

import com.org.entportal.response.MultiAreaUserLinkResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MultiAreaUserLinkResponseProcessor {

    public MultiAreaUserLinkResponse processMultiAreaUserLinkResponse(Map response) {
        MultiAreaUserLinkResponse multiAreaUserLinkResponse = new MultiAreaUserLinkResponse();

        List c1 = (ArrayList) response.get("C1");

        multiAreaUserLinkResponse.setRetText((String) response.get("RETTEXT"));
        multiAreaUserLinkResponse.setC1(c1);

        return multiAreaUserLinkResponse;
    }
}
