package com.org.entportal.response.report.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.org.entportal.response.CustWalletLedgerResponse;

@Component
public class CustWalletLedgerResponseProcessor {

	public CustWalletLedgerResponse custWalletLedger(Map response) {
		CustWalletLedgerResponse custWalletLedgerResponse = new CustWalletLedgerResponse();
		
		List c1 = (ArrayList) response.get("c1");
		
		custWalletLedgerResponse.setC1(c1);
		
		return custWalletLedgerResponse;
	}
}
