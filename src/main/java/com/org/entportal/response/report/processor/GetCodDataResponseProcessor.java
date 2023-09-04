package com.org.entportal.response.report.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.org.entportal.response.GetCodDataResponse;

@Component
public class GetCodDataResponseProcessor {

	public GetCodDataResponse getCodDataResponse(Map response) {
		GetCodDataResponse getCodDataResponse = new GetCodDataResponse();

		List mcpaytype1 = (ArrayList) response.get("mcpaytype1");
		List c1 = (ArrayList) response.get("c1");
		List c2 = (ArrayList) response.get("c2");
		List c4 = (ArrayList) response.get("c4");
		List strdata = (ArrayList) response.get("strdata");

		getCodDataResponse.setMcpaytype1(mcpaytype1);
		getCodDataResponse.setC1(c1);
		getCodDataResponse.setC2(c2);
		getCodDataResponse.setC4(c4);
		getCodDataResponse.setStrdata(strdata);

		return getCodDataResponse;
	}
}
