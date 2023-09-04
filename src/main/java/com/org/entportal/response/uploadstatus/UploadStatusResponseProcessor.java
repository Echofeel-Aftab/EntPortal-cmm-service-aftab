package com.org.entportal.response.uploadstatus;

import com.org.entportal.response.UploadStatusResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UploadStatusResponseProcessor {

    public UploadStatusResponse processUploadStatusResponse(Map response) {
        UploadStatusResponse uploadStatusResponse = new UploadStatusResponse();

        List c1 = (ArrayList) response.get("C1");

        uploadStatusResponse.setC1(c1);

        return uploadStatusResponse;
    }

}
