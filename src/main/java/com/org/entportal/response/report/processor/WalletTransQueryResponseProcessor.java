package com.org.entportal.response.report.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.org.entportal.response.WalletTransQueryResponse;

@Component
public class WalletTransQueryResponseProcessor {

	public WalletTransQueryResponse walletTransQuery(Map response) {
		WalletTransQueryResponse walletTransQueryResponse = new WalletTransQueryResponse();
		
		List c1 = (ArrayList) response.get("c1");
		
		walletTransQueryResponse.setC1(c1);
		
		return walletTransQueryResponse;
	}
}
