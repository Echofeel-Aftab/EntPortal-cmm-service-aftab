package com.org.entportal.service.impl;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.request.AddActionPaySlipRequest;
import com.org.entportal.request.GenerateDepositSlipRequest;
import com.org.entportal.request.SubmitPaySlipRequest;
import com.org.entportal.response.AddActionPaySlipResponse;
import com.org.entportal.response.GenerateDepositSlipResponse;
import com.org.entportal.response.SubmitPaySlipResponse;
import com.org.entportal.response.depositslipgeneration.processor.AddActionPaySlipResponseProcessor;
import com.org.entportal.response.depositslipgeneration.processor.GenerateDepositSlipResponseProcessor;
import com.org.entportal.response.depositslipgeneration.processor.SubmitPaySlipResponseProcessor;
import com.org.entportal.service.DepositSlipGenerationService;
import com.org.entportal.spcall.StoredProcedureCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class DepositSlipGenerationServiceImpl implements DepositSlipGenerationService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StoredProcedureCall spcall;

    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    private GenerateDepositSlipResponseProcessor generateDepositSlipProcessor;
    @Autowired
    private SubmitPaySlipResponseProcessor submitPaySlipProcessor;
    @Autowired
    private AddActionPaySlipResponseProcessor addActionPaySlipProcessor;

    @Override
    public Optional<GenerateDepositSlipResponse> generateDepositSlip(
            GenerateDepositSlipRequest generateDepositSlipRequest) {
        GenerateDepositSlipResponse response = null;

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " generateDepositSlip Entrys");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("mccollempcd", generateDepositSlipRequest.getMccollempcd())
                .addValue("mccollloccd", generateDepositSlipRequest.getMccollloccd())
                .addValue("pcod", generateDepositSlipRequest.getPcod())
                .addValue("pcsubtype", generateDepositSlipRequest.getPcsubtype())
                .addValue("pctype", generateDepositSlipRequest.getPctype())
                .addValue("pcwhich", generateDepositSlipRequest.getPcwhich());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2").withProcedureName("generate_depositslip");

        Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "generate_depositslip");

        if (res != null) {
            response = generateDepositSlipProcessor.generateDepositSlip(res);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " generateDepositSlip Exit");
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }


    @Override
    public Optional<SubmitPaySlipResponse> submitPaySlip(SubmitPaySlipRequest submitPaySlipRequest) {
        SubmitPaySlipResponse response = null;

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " submitPaySlip Entrys");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pcaccno", submitPaySlipRequest.getPcaccno())
                .addValue("pcarea", submitPaySlipRequest.getPcarea())
                .addValue("pcbankcd", submitPaySlipRequest.getPcbankcd())
                .addValue("pcbankcddsp", submitPaySlipRequest.getPcbankcddsp())
                .addValue("pcbankcddsp", submitPaySlipRequest.getPcbankcddsp())
                .addValue("pcod", submitPaySlipRequest.getPcod())
                .addValue("pcsubtype", submitPaySlipRequest.getPcsubtype())
                .addValue("pctype", submitPaySlipRequest.getPctype())
                .addValue("pcwhich", submitPaySlipRequest.getPcwhich())
                .addValue("pn1000denom", submitPaySlipRequest.getPn1000denom())
                .addValue("pn100denom", submitPaySlipRequest.getPn100denom())
                .addValue("pn10denom", submitPaySlipRequest.getPn10denom())
                .addValue("pn1denom", submitPaySlipRequest.getPn1denom())
                .addValue("pn2000denom", submitPaySlipRequest.getPn2000denom())
                .addValue("pn200denom", submitPaySlipRequest.getPn200denom())
                .addValue("pn20denom", submitPaySlipRequest.getPn20denom())
                .addValue("pn2denom", submitPaySlipRequest.getPn2denom())
                .addValue("pn500denom", submitPaySlipRequest.getPn500denom())
                .addValue("pn50denom", submitPaySlipRequest.getPn50denom())
                .addValue("pn5denom", submitPaySlipRequest.getPn5denom())
                .addValue("pnchanges", submitPaySlipRequest.getPnchanges())
                .addValue("pntotal", submitPaySlipRequest.getPntotal())
                .addValue("pntotamt", submitPaySlipRequest.getPntotamt())
                .addValue("psubmit", submitPaySlipRequest.getPsubmit())
                .addValue("mccollloccd", submitPaySlipRequest.getMccollloccd())
                .addValue("mccollempcd", submitPaySlipRequest.getMccollempcd());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("webdcr2").withProcedureName("submit_pay_slip");

        Object res = spcall.callSProc(simpleJdbcCall, params, "submit_pay_slip");

        if (res != null) {
            response = submitPaySlipProcessor.submitPaySlip((Map) res);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " submitPaySlip Exit");
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);
    }


    @Override
    public Optional<AddActionPaySlipResponse> addActionPaySlip(AddActionPaySlipRequest addActionPaySlipRequest) {
        AddActionPaySlipResponse response = null;

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " addActionPaySlip Entrys");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("mccustcode", addActionPaySlipRequest.getMccustcode())
                .addValue("mcdetailsrno", addActionPaySlipRequest.getMcdetailsrno())
                .addValue("mcreasoncd", addActionPaySlipRequest.getMcreasoncd())
                .addValue("pcserialno", addActionPaySlipRequest.getPcserialno())
                .addValue("pmcpaytype", addActionPaySlipRequest.getPmcpaytype());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("webdcr2").withProcedureName("add_action_payslip");

        Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "add_action_payslip");

        if (res != null) {
            response = addActionPaySlipProcessor.addActionPaySlip(res);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " addActionPaySlip Exit");
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }

}
