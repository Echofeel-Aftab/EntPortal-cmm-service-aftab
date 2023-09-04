package com.org.entportal.service.impl;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.request.BankBranchRequest;
import com.org.entportal.request.BankRequest;
import com.org.entportal.response.BankBranchResponse;
import com.org.entportal.response.BankResponse;
import com.org.entportal.response.bank.processor.BankResponseProcessor;
import com.org.entportal.service.BankService;
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

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;
    @Autowired
    BankResponseProcessor bankResponseProcessor;
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
    public Optional<BankResponse> bankAdd(BankRequest bankRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " bankAdd Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource().addValue("pcbankcd", bankRequest.getBankCd())
                .addValue("pcbankdesc", bankRequest.getBankDesc()).addValue("pcacc", bankRequest.getAcc())
                .addValue("pcbanktype", bankRequest.getBankType()).addValue("pcnotinuse", bankRequest.getNotInUse())
                .addValue("Submit", bankRequest.getSubmit());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("bank_add");

        logger.info("IMPL==> bankAdd==> simpleJdbcCall=" + simpleJdbcCall);

        Map res = (Map) spcall.callSProc(simpleJdbcCall, inawb, "bank_add");
        logger.info("IMPL==> BankResponse ==> bank_add res= {}", res);
        BankResponse response = new BankResponse();
        if (res != null) {
            logger.info("IMPL==> BankResponse ==> CUR_PCBANKTYPE1 res=" + res.get("PCBANKTYPE1"));
            logger.info("IMPL==> BankResponse ==> CUR_PCNOTINUSE1 res=" + res.get("PCNOTINUSE1"));
            response = bankResponseProcessor.processBankResponse(res);
        }
        logger.info("IMPL==> QueryDataResponse==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " bankAdd Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);
    }

    @Override
    public Optional<BankBranchResponse> bankBranchMaster(BankBranchRequest bankBranchRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " bankBranchMaster Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource().addValue("pcbankcd", bankBranchRequest.getBankCd())
                .addValue("pcloc", bankBranchRequest.getLoc()).addValue("pcbnkcd", bankBranchRequest.getBnkCd())
                .addValue("pcupdflg", bankBranchRequest.getUpdFlg())
                .addValue("pcupdbankcd", bankBranchRequest.getUpdBankcd())
                .addValue("pcupdbankflg", bankBranchRequest.getUpdBankFlg())
                .addValue("psubmit", bankBranchRequest.getSubmit());

        logger.info("IMPL==> bankBranchMaster==> simpleJdbcCall=" + simpleJdbcCall);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("bank_branch_master");

        Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, inawb, "bank_branch_master");

        BankBranchResponse response = new BankBranchResponse();
        logger.info("IMPL==> BankResponse ==> res= {}", res);
        if (res != null) {
            logger.info("IMPL==> BankResponse ==> c1 res=" + res.get("C1"));
            logger.info("IMPL==> BankResponse ==> c2 res=" + res.get("C2"));
            response = bankResponseProcessor.processBankBranchResponse(res);
        }
        logger.info("IMPL==> BankBranchResponse ==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " bankBranchMaster Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }

}
