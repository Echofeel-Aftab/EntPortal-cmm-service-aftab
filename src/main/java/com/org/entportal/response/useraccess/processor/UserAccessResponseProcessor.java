package com.org.entportal.response.useraccess.processor;

import com.org.entportal.response.UserAccessResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UserAccessResponseProcessor {

    public UserAccessResponse processUserAccessResponse(Map response) {
        UserAccessResponse userAccessResponse = new UserAccessResponse();

        List cur = (ArrayList) response.get("CUR");
        List cur1 = (ArrayList) response.get("CUR1");
        List cur2 = (ArrayList) response.get("CUR2");
        userAccessResponse.setMcempregion((String) response.get("mcempregion"));
        userAccessResponse.setMcemparea((String) response.get("mcemparea"));
        userAccessResponse.setMcbranch((String) response.get("mcbranch"));
        userAccessResponse.setMclocbranch((String) response.get("mclocbranch"));

        userAccessResponse.setCur(cur);
        userAccessResponse.setCur1(cur1);
        userAccessResponse.setCur2(cur2);

        return userAccessResponse;
    }
}
