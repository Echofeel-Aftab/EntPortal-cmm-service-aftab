package com.org.entportal.response.report.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.org.entportal.response.DepositApprovalReportResponse;

@Component
public class DepositApprovalReportResponseProcessor {

	public DepositApprovalReportResponse depositApprovalReport(Map response) {
		DepositApprovalReportResponse depositApprovalReportResponse = new DepositApprovalReportResponse();

		List pcregion1 = (ArrayList) response.get("pcregion1");
		List c1 = (ArrayList) response.get("c1");

		depositApprovalReportResponse.setPcregion1(pcregion1);
		depositApprovalReportResponse.setC1(c1);

		return depositApprovalReportResponse;
	}
}
