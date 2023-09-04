package com.org.entportal.response.report.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.org.entportal.response.CodOutstandingResponse;

@Component
public class CodOutstandingResponseProcessor {

	public CodOutstandingResponse codOutstandingResponse(Map response) {
		CodOutstandingResponse codOutstandingResponse = new CodOutstandingResponse();

		List mainType = (ArrayList) response.get("mmainType");
		List prodCode = (ArrayList) response.get("mProdCode");
		List status = (ArrayList) response.get("mStatus");
		List outstanding = (ArrayList) response.get("moutstanding");
		List groupCode = (ArrayList) response.get("mcGroupCode");

		codOutstandingResponse.setMainType(mainType);
		codOutstandingResponse.setProdCode(prodCode);
		codOutstandingResponse.setStatus(status);
		codOutstandingResponse.setOutstanding(outstanding);
		codOutstandingResponse.setGroupCode(groupCode);

		return codOutstandingResponse;
	}
}
