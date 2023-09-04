package com.org.entportal.response.entryscreen.processor;

import com.org.entportal.response.EntryScreenResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EntryScreenResponseProcessor {

    public EntryScreenResponse processEntryScreenResponse(Map response) {
        EntryScreenResponse entryScreenResponse = new EntryScreenResponse();

        List collMd = (ArrayList) response.get("PCCOLLMD");
        List outStnd1 = (ArrayList) response.get("OUTSTND1");
        List outStnd = (ArrayList) response.get("OUTSTND");

        entryScreenResponse.setCollMd(collMd);
        entryScreenResponse.setOutstnd1(outStnd1);
        entryScreenResponse.setOutstnd(outStnd);
        entryScreenResponse.setCustpayamt((BigDecimal) response.get("mncustpayamt"));
        entryScreenResponse.setInCount((BigDecimal) response.get("ln_count"));
        entryScreenResponse.setMcemplname((String) response.get("mcemplname"));

        return entryScreenResponse;
    }

}
