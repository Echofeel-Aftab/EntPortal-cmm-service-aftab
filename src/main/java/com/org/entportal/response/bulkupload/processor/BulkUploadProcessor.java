package com.org.entportal.response.bulkupload.processor;

import com.org.entportal.response.BulkUploadResponse;
import com.org.entportal.response.CollectionType;
import com.org.entportal.response.PayType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BulkUploadProcessor {

    public BulkUploadResponse processBulkUploadResponse(Map response) {
        BulkUploadResponse bulkUploadResponse = new BulkUploadResponse();

        List collType = (ArrayList<CollectionType>) response.get("PCCOLLTYPE");
        List payType = (ArrayList<PayType>) response.get("PCPAYTYPE");

        bulkUploadResponse.setCollType(collType);
        bulkUploadResponse.setPayType(payType);

        return bulkUploadResponse;
    }

}
