package com.org.entportal.response.bank.processor;

import com.org.entportal.response.BankBranchResponse;
import com.org.entportal.response.BankResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BankResponseProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BankResponse processBankResponse(Map response) {
        BankResponse bankResponse = new BankResponse();

        List bankType = (ArrayList) response.get("PCBANKTYPE1");
        List notInUse = (ArrayList) response.get("PCNOTINUSE1");

        bankResponse.setRetTest((String) response.get("RETTEXT"));
        bankResponse.setBankType(bankType);
        bankResponse.setNotInUse(notInUse);

        return bankResponse;
    }

    public BankBranchResponse processBankBranchResponse(Map response) {
        BankBranchResponse bankBranchResponse = new BankBranchResponse();

        List c1 = (ArrayList) response.get("C1");
        List c2 = (ArrayList) response.get("C2");

        bankBranchResponse.setMsg((String) response.get("MSG"));
        bankBranchResponse.setMsg1((String) response.get("MSG1"));
        bankBranchResponse.setMsg2((String) response.get("MSG2"));
        bankBranchResponse.setC1(c1);
        bankBranchResponse.setC2(c2);

        return bankBranchResponse;
    }

}
