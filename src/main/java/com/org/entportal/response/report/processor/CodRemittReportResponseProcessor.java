package com.org.entportal.response.report.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.org.entportal.response.CodRemittReportResponse;

@Component
public class CodRemittReportResponseProcessor {

	public CodRemittReportResponse codRemittReportResponse(Map response) {
		CodRemittReportResponse codRemittReportResponse = new CodRemittReportResponse();
		
		List c1 = (ArrayList) response.get("c1");
		List c2 = (ArrayList) response.get("c2");
		
		codRemittReportResponse.setC1(c1);
		codRemittReportResponse.setC2(c2);
		
		return codRemittReportResponse;
	}
}
