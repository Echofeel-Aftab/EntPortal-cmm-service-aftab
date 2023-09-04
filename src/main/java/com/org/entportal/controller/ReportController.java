package com.org.entportal.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.common.ResponseUtil;
import com.org.entportal.common.ServiceResponse;
import com.org.entportal.request.CodOutstandingRequest;
import com.org.entportal.request.CodRemittReportRequest;
import com.org.entportal.request.CollectionReportRequest;
import com.org.entportal.request.CustWalletLedgerRequest;
import com.org.entportal.request.DepositApprovalReportRequest;
import com.org.entportal.request.DutyReportRequest;
import com.org.entportal.request.GetCodDataRequest;
import com.org.entportal.request.UserListingRequest;
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
import com.org.entportal.service.ReportService;

@ComponentScan
@RestController
@RequestMapping(path = "/cmm/report", consumes = "application/json", produces = "application/json")
public class ReportController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MessageSource messageSource;

	@Autowired
	private ReportService reportService;

	@Autowired
	ResponseUtil<CodOutstandingResponse> codOutstandingResponseUtil;

	@Autowired
	ResponseUtil<CollectionReportResponse> collectionReportResponseUtil;

	@Autowired
	ResponseUtil<UserListingResponse> userListingResponseUtil;

	@Autowired
	ResponseUtil<GetCodDataResponse> getCodDataResponseUtil;

	@Autowired
	ResponseUtil<CodRemittReportResponse> codremittreportResponseUtil;

	@Autowired
	ResponseUtil<CustWalletLedgerResponse> custWalletLedgerResponseUtil;

	@Autowired
	ResponseUtil<DepositApprovalReportResponse> depositApprovalReportResponseUtil;

	@Autowired
	ResponseUtil<DutyReportResponse> dutyReportResponseUtil;

	@Autowired
	ResponseUtil<WalletTransQueryResponse> walletTransQueryResponseUtil;

	@PostMapping(path = "codoutstanding")
	public ResponseEntity<ServiceResponse<CodOutstandingResponse>> codoutstanding(
			@Valid @RequestBody CodOutstandingRequest codOutstandingRequest, BindingResult result,
			HttpServletRequest request) throws Exception {

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.API_NAME + " codoutstanding");

		if (result.hasErrors()) {
			String validationErrors = getValidationErrorMessage(result);
			logger.info(validationErrors);
			return codOutstandingResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
		}
		try {
			CodOutstandingResponse codOutstandingResponse = reportService.codoutstanding(codOutstandingRequest)
					.orElseGet(() -> null);
			return codOutstandingResponseUtil.buildResponse(HttpStatus.OK,
					messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
					codOutstandingResponse);
		} finally {
			long exitTime = System.currentTimeMillis();
			logger.info(LogParamsEnum.API_NAME + " codoutstanding Exit");
			logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
		}
	}

	@PostMapping(path = "collectionreport")
	public ResponseEntity<ServiceResponse<CollectionReportResponse>> collectionreport(
			@RequestBody CollectionReportRequest collectionReportRequest, BindingResult result,
			HttpServletRequest request) throws Exception {

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.API_NAME + " collectionreport");

		if (result.hasErrors()) {
			String validationErrors = getValidationErrorMessage(result);
			logger.info(validationErrors);
			return collectionReportResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors,
					false);
		}
		try {
			CollectionReportResponse codOutstandingResponse = reportService.collectionreport().orElseGet(() -> null);
			return collectionReportResponseUtil.buildResponse(HttpStatus.OK,
					messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
					codOutstandingResponse);
		} finally {
			long exitTime = System.currentTimeMillis();
			logger.info(LogParamsEnum.API_NAME + " collectionreport Exit");
			logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
		}
	}

	@PostMapping(path = "userListing")
	public ResponseEntity<ServiceResponse<UserListingResponse>> userListing(
			@RequestBody UserListingRequest userListingRequest, BindingResult result, HttpServletRequest request)
			throws Exception {

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.API_NAME + " collectionreport");

		if (result.hasErrors()) {
			String validationErrors = getValidationErrorMessage(result);
			logger.info(validationErrors);
			return userListingResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
		}
		try {
			UserListingResponse userListingResponse = reportService.userListing().orElseGet(() -> null);
			return userListingResponseUtil.buildResponse(HttpStatus.OK,
					messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
					userListingResponse);
		} finally {
			long exitTime = System.currentTimeMillis();
			logger.info(LogParamsEnum.API_NAME + " collectionreport Exit");
			logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
		}
	}

	@PostMapping(path = "getcoddata")
	public ResponseEntity<ServiceResponse<GetCodDataResponse>> getCodData(
			@RequestBody GetCodDataRequest getCodDataRequest, BindingResult result, HttpServletRequest request)
			throws Exception {

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.API_NAME + " getCodData");

		if (result.hasErrors()) {
			String validationErrors = getValidationErrorMessage(result);
			logger.info(validationErrors);
			return getCodDataResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
		}
		try {
			GetCodDataResponse getCodDataResponse = reportService.getCodDataResponse(getCodDataRequest)
					.orElseGet(() -> null);
			return getCodDataResponseUtil.buildResponse(HttpStatus.OK,
					messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
					getCodDataResponse);
		} finally {
			long exitTime = System.currentTimeMillis();
			logger.info(LogParamsEnum.API_NAME + " getCodData Exit");
			logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
		}
	}

	@PostMapping(path = "codremittreport")
	public ResponseEntity<ServiceResponse<CodRemittReportResponse>> codremittreport(
			@RequestBody CodRemittReportRequest codRemittReportRequest, BindingResult result,
			HttpServletRequest request) throws Exception {

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.API_NAME + " codremittreport");

		if (result.hasErrors()) {
			String validationErrors = getValidationErrorMessage(result);
			logger.info(validationErrors);
			return codremittreportResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
		}
		try {
			CodRemittReportResponse codRemittReportResponse = reportService.codRemittReport(codRemittReportRequest)
					.orElseGet(() -> null);
			return codremittreportResponseUtil.buildResponse(HttpStatus.OK,
					messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
					codRemittReportResponse);
		} finally {
			long exitTime = System.currentTimeMillis();
			logger.info(LogParamsEnum.API_NAME + " codremittreport Exit");
			logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
		}
	}

	@PostMapping(path = "custWalletLedger")
	public ResponseEntity<ServiceResponse<CustWalletLedgerResponse>> custWalletLedger(
			@RequestBody CustWalletLedgerRequest custWalletLedgerRequest, BindingResult result,
			HttpServletRequest request) throws Exception {

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.API_NAME + " custWalletLedger");

		if (result.hasErrors()) {
			String validationErrors = getValidationErrorMessage(result);
			logger.info(validationErrors);
			return custWalletLedgerResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors,
					false);
		}
		try {
			CustWalletLedgerResponse custWalletLedgerResponse = reportService.custWalletLedger(custWalletLedgerRequest)
					.orElseGet(() -> null);
			return custWalletLedgerResponseUtil.buildResponse(HttpStatus.OK,
					messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
					custWalletLedgerResponse);
		} finally {
			long exitTime = System.currentTimeMillis();
			logger.info(LogParamsEnum.API_NAME + " custWalletLedger Exit");
			logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
		}
	}

	@PostMapping(path = "depositApprovalReport")
	public ResponseEntity<ServiceResponse<DepositApprovalReportResponse>> depositApprovalReport(
			@RequestBody DepositApprovalReportRequest depositApprovalReportRequest, BindingResult result,
			HttpServletRequest request) throws Exception {

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.API_NAME + " depositApprovalReport");

		if (result.hasErrors()) {
			String validationErrors = getValidationErrorMessage(result);
			logger.info(validationErrors);
			return depositApprovalReportResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors,
					false);
		}
		try {
			DepositApprovalReportResponse depositApprovalReportResponse = reportService
					.depositApprovalReport(depositApprovalReportRequest).orElseGet(() -> null);
			return depositApprovalReportResponseUtil.buildResponse(HttpStatus.OK,
					messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
					depositApprovalReportResponse);
		} finally {
			long exitTime = System.currentTimeMillis();
			logger.info(LogParamsEnum.API_NAME + " depositApprovalReport Exit");
			logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
		}
	}

	@PostMapping(path = "dutyReport")
	public ResponseEntity<ServiceResponse<DutyReportResponse>> dutyReport(
			@RequestBody DutyReportRequest dutyReportRequest, BindingResult result, HttpServletRequest request)
			throws Exception {

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.API_NAME + " dutyReport");

		if (result.hasErrors()) {
			String validationErrors = getValidationErrorMessage(result);
			logger.info(validationErrors);
			return dutyReportResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
		}
		try {
			DutyReportResponse dutyReportResponse = reportService.dutyReport().orElseGet(() -> null);
			return dutyReportResponseUtil.buildResponse(HttpStatus.OK,
					messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
					dutyReportResponse);
		} finally {
			long exitTime = System.currentTimeMillis();
			logger.info(LogParamsEnum.API_NAME + " dutyReport Exit");
			logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
		}
	}

	@PostMapping(path = "walletTransQuery")
	public ResponseEntity<ServiceResponse<WalletTransQueryResponse>> walletTransQuery(
			@RequestBody WalletTransQueryRequest walletTransQueryRequest, BindingResult result,
			HttpServletRequest request) throws Exception {

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.API_NAME + " walletTransQuery");

		if (result.hasErrors()) {
			String validationErrors = getValidationErrorMessage(result);
			logger.info(validationErrors);
			return walletTransQueryResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors,
					false);
		}
		try {
			WalletTransQueryResponse walletTransQueryResponse = reportService.walletTransQuery(walletTransQueryRequest)
					.orElseGet(() -> null);
			return walletTransQueryResponseUtil.buildResponse(HttpStatus.OK,
					messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
					walletTransQueryResponse);
		} finally {
			long exitTime = System.currentTimeMillis();
			logger.info(LogParamsEnum.API_NAME + " walletTransQuery Exit");
			logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
		}
	}

	private String getValidationErrorMessage(BindingResult bindingResult) {
		StringBuilder sb = new StringBuilder();
		sb.append(bindingResult.getErrorCount()).append(" error(s): ");
		for (ObjectError error : bindingResult.getAllErrors()) {
			logger.error("VALIIDATION MSG:"
					+ messageSource.getMessage(error.getDefaultMessage(), null, Locale.getDefault()));
			System.out.print("VALIIDATION MSG:"
					+ messageSource.getMessage(error.getDefaultMessage(), null, Locale.getDefault()));
			sb.append("[").append(messageSource.getMessage(error.getDefaultMessage(), null, Locale.getDefault()))
					.append("] ");
		}
		logger.error("getValidationErrorMessage=" + sb.toString());
		System.out.print("getValidationErrorMessage=" + sb.toString());
		return sb.toString();
	}
}
