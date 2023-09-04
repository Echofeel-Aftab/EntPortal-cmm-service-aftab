package com.org.entportal.service;

import java.util.Optional;

import com.org.entportal.request.CodOutstandingRequest;
import com.org.entportal.request.CodRemittReportRequest;
import com.org.entportal.request.CustWalletLedgerRequest;
import com.org.entportal.request.DepositApprovalReportRequest;
import com.org.entportal.request.DutyReportRequest;
import com.org.entportal.request.GetCodDataRequest;
import com.org.entportal.request.WalletTransQueryRequest;
import com.org.entportal.response.CodOutstandingResponse;
import com.org.entportal.response.CodRemittReportResponse;
import com.org.entportal.response.CollectionReportResponse;
import com.org.entportal.response.CustWalletLedgerResponse;
import com.org.entportal.response.DepositApprovalReportResponse;
import com.org.entportal.response.DutyReportResponse;
import com.org.entportal.response.GetCodDataResponse;
import com.org.entportal.response.UserListingResponse;
import com.org.entportal.response.WalletTransQueryResponse;

public interface ReportService {

	 public Optional<CodOutstandingResponse> codoutstanding(CodOutstandingRequest codOutstandingRequest);
	 
	 public Optional<CollectionReportResponse> collectionreport();
	 
	 public Optional<UserListingResponse> userListing();
	 
	 public Optional<GetCodDataResponse> getCodDataResponse(GetCodDataRequest getCodDataRequest);
	 
	 public Optional<CodRemittReportResponse> codRemittReport(CodRemittReportRequest codRemittReportRequest);
	 
	 public Optional<CustWalletLedgerResponse> custWalletLedger(CustWalletLedgerRequest custWalletLedgerRequest);
	 
	 public Optional<DepositApprovalReportResponse> depositApprovalReport(DepositApprovalReportRequest depositApprovalReportRequest);
	 
	 public Optional<DutyReportResponse> dutyReport();
	 
	 public Optional<WalletTransQueryResponse> walletTransQuery(WalletTransQueryRequest walletTransQueryRequest);
	 
}
