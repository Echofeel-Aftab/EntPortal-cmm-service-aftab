package com.org.entportal.response.entryscreeninv.processor;

import com.org.entportal.response.EntryScreenInvResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EntryScreenInvReponseProcessor {

    public EntryScreenInvResponse processEntryScreenInvResponse(Map response) {
        EntryScreenInvResponse entryScreenInvResponse = new EntryScreenInvResponse();

        List paytype1 = (ArrayList) response.get("PCPAUTYPE1");
        List colltype1 = (ArrayList) response.get("PCCOLLTYPE1");
        List outStnd = (ArrayList) response.get("OUTSTND");

        entryScreenInvResponse.setCustname((String) response.get("LV_CUSTNAME"));
        entryScreenInvResponse.setCustcode1((String) response.get("LV_CUSTCODE1"));
        entryScreenInvResponse.setPaytype1(paytype1);
        entryScreenInvResponse.setColltype1(colltype1);
        entryScreenInvResponse.setOutstnd(outStnd);

        return entryScreenInvResponse;
    }

}
