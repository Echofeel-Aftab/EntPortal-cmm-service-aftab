package com.org.entportal.service.impl;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.request.CodOutstandingRequest;
import com.org.entportal.request.CodRemittReportRequest;
import com.org.entportal.request.CustWalletLedgerRequest;
import com.org.entportal.request.DepositApprovalReportRequest;
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
import com.org.entportal.response.report.processor.CodOutstandingResponseProcessor;
import com.org.entportal.response.report.processor.CodRemittReportResponseProcessor;
import com.org.entportal.response.report.processor.CollectionReportResponseProcessor;
import com.org.entportal.response.report.processor.CustWalletLedgerResponseProcessor;
import com.org.entportal.response.report.processor.DepositApprovalReportResponseProcessor;
import com.org.entportal.response.report.processor.DutyReportResponseProcessor;
import com.org.entportal.response.report.processor.GetCodDataResponseProcessor;
import com.org.entportal.response.report.processor.UserListingResponseProcessor;
import com.org.entportal.response.report.processor.WalletTransQueryResponseProcessor;
import com.org.entportal.service.ReportService;
import com.org.entportal.spcall.StoredProcedureCall;

@Service
public class ReportServiceImpl implements ReportService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MessageSource messageSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private StoredProcedureCall spcall;

	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	private CodOutstandingResponseProcessor codOutstandingResponseProcessor;

	@Autowired
	private CollectionReportResponseProcessor collectionReportResponseProcessor;

	@Autowired
	private UserListingResponseProcessor userListingResponseProcessor;

	@Autowired
	private GetCodDataResponseProcessor getCodDataResponseProcessor;

	@Autowired
	private CodRemittReportResponseProcessor codRemittReportResponseProcessor;

	@Autowired
	private CustWalletLedgerResponseProcessor custWalletLedgerResponseProcessor;

	@Autowired
	private DepositApprovalReportResponseProcessor depositApprovalReportResponseProcessor;

	@Autowired
	private DutyReportResponseProcessor dutyReportResponseProcessor;

	@Autowired
	private WalletTransQueryResponseProcessor walletTransQueryResponseProcessor;

	@Override
	public Optional<CodOutstandingResponse> codoutstanding(CodOutstandingRequest codOutstandingRequest) {
		CodOutstandingResponse response = null;

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " codoutstanding Entry");

		SqlParameterSource params = new MapSqlParameterSource().addValue("mempregion",
				codOutstandingRequest.getEmpregion());

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2")
				.withProcedureName("codoutstanding");

		Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "codoutstanding");

		if (res != null) {
			response = codOutstandingResponseProcessor.codOutstandingResponse(res);
		}

		long exitTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " codoutstanding Exit");
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
		return Optional.of(response);
	}

	@Override
	public Optional<CollectionReportResponse> collectionreport() {
		CollectionReportResponse response = null;

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " codoutstanding Entry");

		SqlParameterSource params = new MapSqlParameterSource();

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2")
				.withProcedureName("collectionreport");

		Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "collectionreport");

		if (res != null) {
			response = collectionReportResponseProcessor.collectionreport(res);
		}

		long exitTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " collectionreport Exit");
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
		return Optional.of(response);
	}

	@Override
	public Optional<UserListingResponse> userListing() {

		UserListingResponse response = null;

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " userListing Entry");

		SqlParameterSource params = new MapSqlParameterSource();

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2").withProcedureName("userListing");

		Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "userListing");

		if (res != null) {
			response = userListingResponseProcessor.userListing(res);
		}

		long exitTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " userListing Exit");
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
		return Optional.of(response);
	}

	@Override
	public Optional<GetCodDataResponse> getCodDataResponse(GetCodDataRequest getCodDataRequest) {
		GetCodDataResponse response = null;

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " getCodDataResponse Entry");

		SqlParameterSource params = new MapSqlParameterSource().addValue("mdstartdt", getCodDataRequest.getMdstartdt())
				.addValue("mdenddt", getCodDataRequest.getMdenddt()).addValue("mcflg", getCodDataRequest.getMcflg())
				.addValue("msubmit", getCodDataRequest.getMsubmit())
				.addValue("mcpaytype", getCodDataRequest.getMcpaytype())
				.addValue("mcpayslipno", getCodDataRequest.getMcpayslipno())
				.addValue("mcpancard", getCodDataRequest.getMcpancard())
				.addValue("mdagedate", getCodDataRequest.getMdagedate())
				.addValue("mdawblist", getCodDataRequest.getMdawblist());

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2").withProcedureName("get_cod_data");

		Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "get_cod_data");

		if (res != null) {
			response = getCodDataResponseProcessor.getCodDataResponse(res);
		}

		long exitTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " getCodDataResponse Exit");
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
		return response == null ? Optional.empty() : Optional.of(response);
	}

	@Override
	public Optional<CodRemittReportResponse> codRemittReport(CodRemittReportRequest codRemittReportRequest) {
		CodRemittReportResponse response = null;

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " codRemittReport Entry");

		SqlParameterSource params = new MapSqlParameterSource().addValue("MRTYPE", codRemittReportRequest.getMrtype())
				.addValue("mpannumber", codRemittReportRequest.getMpannumber())
				.addValue("mfromdt", codRemittReportRequest.getMfromdt());

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2")
				.withProcedureName("codremittreport");

		Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "codremittreport");

		if (res != null) {
			response = codRemittReportResponseProcessor.codRemittReportResponse(res);
		}

		long exitTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " codRemittReport Exit");
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
		return response == null ? Optional.empty() : Optional.of(response);
	}

	@Override
	public Optional<CustWalletLedgerResponse> custWalletLedger(CustWalletLedgerRequest custWalletLedgerRequest) {
		CustWalletLedgerResponse response = null;

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " custWalletLedger Entry");

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("morgarea", custWalletLedgerRequest.getMorgarea())
				.addValue("mcustcode", custWalletLedgerRequest.getMcustcode())
				.addValue("mfromdt", custWalletLedgerRequest.getMfromdt())
				.addValue("mtodt", custWalletLedgerRequest.getMtodt())
				.addValue("msubmit", custWalletLedgerRequest.getMsubmit());

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2")
				.withProcedureName("CustWalletLedger");

		Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "CustWalletLedger");

		if (res != null) {
			response = custWalletLedgerResponseProcessor.custWalletLedger(res);
		}

		long exitTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " custWalletLedger Exit");
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
		return response == null ? Optional.empty() : Optional.of(response);
	}

	@Override
	public Optional<DepositApprovalReportResponse> depositApprovalReport(
			DepositApprovalReportRequest depositApprovalReportRequest) {

		DepositApprovalReportResponse response = null;

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " depositApprovalReport Entry");

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("pcregion", depositApprovalReportRequest.getPcregion())
				.addValue("pcolloc", depositApprovalReportRequest.getPcolloc())
				.addValue("pdfromdt", depositApprovalReportRequest.getPdfromdt())
				.addValue("pdtodt", depositApprovalReportRequest.getPdtodt())
				.addValue("psubmit", depositApprovalReportRequest.getPsubmit());

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2")
				.withProcedureName("deposit_approval_report");

		Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params,
				"deposit_approval_report");

		if (res != null) {
			response = depositApprovalReportResponseProcessor.depositApprovalReport(res);
		}

		long exitTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " depositApprovalReport Exit");
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
		return response == null ? Optional.empty() : Optional.of(response);

	}

	@Override
	public Optional<DutyReportResponse> dutyReport() {

		DutyReportResponse response = null;

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " dutyReport Entry");

		SqlParameterSource params = new MapSqlParameterSource();

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2").withProcedureName("dutyReport");

		Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "dutyReport");

		if (res != null) {
			response = dutyReportResponseProcessor.dutyReport(res);
		}

		long exitTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " dutyReport Exit");
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
		return response == null ? Optional.empty() : Optional.of(response);

	}

	@Override
	public Optional<WalletTransQueryResponse> walletTransQuery(WalletTransQueryRequest walletTransQueryRequest) {

		WalletTransQueryResponse response = null;

		long entryTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " walletTransQuery Entry");

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("mcstartdate", walletTransQueryRequest.getMcstartdate())
				.addValue("mcenddate", walletTransQueryRequest.getMcenddate())
				.addValue("mregion", walletTransQueryRequest.getMregion())
				.addValue("psubmit", walletTransQueryRequest.getPsubmit());

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2")
				.withProcedureName("wallet_trans_query");

		Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "wallet_trans_query");

		if (res != null) {
			response = walletTransQueryResponseProcessor.walletTransQuery(res);
		}

		long exitTime = System.currentTimeMillis();
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " walletTransQuery Exit");
		logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
		return response == null ? Optional.empty() : Optional.of(response);

	}

}
