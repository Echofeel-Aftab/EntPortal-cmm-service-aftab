package com.org.entportal.response.queryprintdepositslip.processor;

import com.org.entportal.response.QueryPaySlipResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class QueryPaySlipResponseProcessor {

    public QueryPaySlipResponse queryPaySlip(Map response) {
        QueryPaySlipResponse queryPaySlipResponse = new QueryPaySlipResponse();

        List detailh = (ArrayList) response.get("detailh");
        List colhead = (ArrayList) response.get("colhead");
        List colhead1 = (ArrayList) response.get("colhead1");

        queryPaySlipResponse.setColhead(colhead);
        queryPaySlipResponse.setColhead1(colhead1);
        queryPaySlipResponse.setDetailh(detailh);

        return queryPaySlipResponse;
    }
}
