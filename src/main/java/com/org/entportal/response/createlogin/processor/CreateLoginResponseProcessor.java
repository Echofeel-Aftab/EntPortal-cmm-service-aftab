package com.org.entportal.response.createlogin.processor;

import com.org.entportal.response.CreateLoginResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CreateLoginResponseProcessor {

    public CreateLoginResponse processCreateLoginResponse(Map response) {
        CreateLoginResponse createLoginResponse = new CreateLoginResponse();

        List c1 = (ArrayList) response.get("C1");
        List c2 = (ArrayList) response.get("C2");
        List role = (ArrayList) response.get("PCROLE");

        createLoginResponse.setLocAccess((ArrayList) response.get("LV_LOCACCESS"));
        createLoginResponse.setC1(c1);
        createLoginResponse.setC2(c2);
        createLoginResponse.setRole(role);

        return createLoginResponse;
    }
}
