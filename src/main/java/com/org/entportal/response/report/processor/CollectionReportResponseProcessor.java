package com.org.entportal.response.report.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.org.entportal.response.CollectionReportResponse;

@Component
public class CollectionReportResponseProcessor {

	public CollectionReportResponse collectionreport(Map response) {
		CollectionReportResponse collectionReportResponse = new CollectionReportResponse();
		
		List mrType = (ArrayList) response.get("mrType");
		
		collectionReportResponse.setMrType(mrType);
		
		return collectionReportResponse;
	}
}
