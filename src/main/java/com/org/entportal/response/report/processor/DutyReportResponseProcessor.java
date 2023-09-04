package com.org.entportal.response.report.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.org.entportal.response.DutyReportResponse;

@Component
public class DutyReportResponseProcessor {

	public DutyReportResponse dutyReport(Map response) {
		DutyReportResponse dutyReportResponse = new DutyReportResponse();

		List pcsel = (ArrayList) response.get("pcsel");
		List pcreg = (ArrayList) response.get("pcreg");

		dutyReportResponse.setPcsel(pcsel);
		dutyReportResponse.setPcreg(pcreg);

		return dutyReportResponse;
	}
}
