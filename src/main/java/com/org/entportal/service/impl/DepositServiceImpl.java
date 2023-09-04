package com.org.entportal.service.impl;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.request.ChangeDepositSlipRequest;
import com.org.entportal.request.DepositGenerationSlipRequest;
import com.org.entportal.response.ChangeDepositSlipResponse;
import com.org.entportal.response.DepositGenerationSlipResponse;
import com.org.entportal.service.DepositService;
import com.org.entportal.spcall.StoredProcedureCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Service
public class DepositServiceImpl implements DepositService {

    private final Logger logger = LoggerFactory.getLogger(DepositServiceImpl.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StoredProcedureCall spcall;

    private SimpleJdbcCall simpleJdbcCall;

    @PostConstruct
    void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public Object changeDepositSlip(ChangeDepositSlipRequest changeDepositSlipRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " changeDepositSlip Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource()
                .addValue("pcpayslipno", changeDepositSlipRequest.getPcpayslipno())
                .addValue("mcdepositsrno", changeDepositSlipRequest.getDepositsrno())
                .addValue("mccollloccd", changeDepositSlipRequest.getCcollLoccd())
                .addValue("mccollempcd", changeDepositSlipRequest.getCcollEmpcd())
                .addValue("mcdcrrole", changeDepositSlipRequest.getCdcrRole())
                .addValue("psubmit", changeDepositSlipRequest.getSubmit());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("query_depositslip").returningResultSet("changeDepositSlipResponse",
                        BeanPropertyRowMapper.newInstance(ChangeDepositSlipResponse.class));

        System.out.println("IMPL==> queryDataEntry==> simpleJdbcCall=" + simpleJdbcCall);

        Object res = spcall.callSProc(simpleJdbcCall, inawb, "changeDepositSlipResponse");

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " changeDepositSlip Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));

        return res;
    }

    @Override
    public Optional<DepositGenerationSlipResponse> depositGenerationSlip(DepositGenerationSlipRequest depositGenerationSlipRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " DepositGeneratioSlip Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource()
                .addValue("pntotamt", depositGenerationSlipRequest.getTotalAmount())
                .addValue("pn2000denom", depositGenerationSlipRequest.getRs2000Denomination())
                .addValue("pn1000denom", depositGenerationSlipRequest.getRs1000Denomination())
                .addValue("pn500denom", depositGenerationSlipRequest.getRs500Denomination())
                .addValue("pn200denom", depositGenerationSlipRequest.getRs200Denomination())
                .addValue("pn100denom", depositGenerationSlipRequest.getRs100Denomination())
                .addValue("pn50denom", depositGenerationSlipRequest.getRs50Denomination())
                .addValue("pn20denom", depositGenerationSlipRequest.getRs20Denomination())
                .addValue("pn10denom", depositGenerationSlipRequest.getRs2Denomination())
                .addValue("pn5denom", depositGenerationSlipRequest.getRs5Denomination())
                .addValue("pn2denom", depositGenerationSlipRequest.getRs2Denomination())
                .addValue("pn1denom", depositGenerationSlipRequest.getRs1Denomination())
                .addValue("pnchanges", depositGenerationSlipRequest.getChanges())
                .addValue("pntotal", depositGenerationSlipRequest.getTotal())
                .addValue("pcaccno", depositGenerationSlipRequest.getAccno())
                .addValue("pcbankcd", depositGenerationSlipRequest.getBankcd())
                .addValue("pcbankcddsp", depositGenerationSlipRequest.getBankcddsp())
                .addValue("psubmit", depositGenerationSlipRequest.getSubmit())
                .addValue("pctype", depositGenerationSlipRequest.getType())
                .addValue("pcsubtype", depositGenerationSlipRequest.getSubtype())
                .addValue("pcwhich", depositGenerationSlipRequest.getForWhom())
                .addValue("pcarea", depositGenerationSlipRequest.getArea())
                .addValue("pcod", depositGenerationSlipRequest.getCodOrOthers());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("submit_pay_slip").returningResultSet("DepositGenerationSlipResponse",
                        BeanPropertyRowMapper.newInstance(DepositGenerationSlipResponse.class));

        System.out.println("IMPL==> queryDataEntry==> simpleJdbcCall=" + simpleJdbcCall);

        Map res = (Map) spcall.callSProc(simpleJdbcCall, inawb, "DepositGenerationSlipResponse");

        DepositGenerationSlipResponse response = null;
        System.out.println("IMPL==> DepositGenerationSlipResponse==>res=" + res.get("CUR_AWBDETAIL"));
        if (res != null) response = (DepositGenerationSlipResponse) res.get("retText");
        System.out.println("IMPL==> getAWBDetails==>response" + response);
        if (null != response) {
            System.out.println("IMPL==> getAWBDetails==>response.get(0)=" + response);
            return Optional.of(response);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " depositGenerationSlip Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));

        return Optional.empty();
    }

}
