package com.org.entportal.service.impl;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.request.OldCurrAmtRequest;
import com.org.entportal.request.QueryDataRequest;
import com.org.entportal.request.SessionflagRequest;
import com.org.entportal.request.ShortRequest;
import com.org.entportal.request.UpdCollAmtRequest;
import com.org.entportal.response.OldCurrAmtResponse;
import com.org.entportal.response.QueryDataResponse;
import com.org.entportal.response.SessionflagResponse;
import com.org.entportal.response.UpdCollAmtResponse;
import com.org.entportal.response.getShortResponse;
import com.org.entportal.response.updcollamt.processor.OldCurrAmtResponseProcessor;
import com.org.entportal.response.updcollamt.processor.UpdCollAmtResponseProcessor;
import com.org.entportal.service.CollectionService;
import com.org.entportal.spcall.StoredProcedureCall;
import oracle.sql.ArrayDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StoredProcedureCall spcall;

    @Autowired
    private UpdCollAmtResponseProcessor updCollAmtResponseProcessor;

    @Autowired
    private OldCurrAmtResponseProcessor oldCurrAmtResponseProcessor;


    private SimpleJdbcCall simpleJdbcCall;

    private static java.sql.Array GetSqlArray(String typeName, java.util.List<String> list, Connection connection) throws SQLException {
        ArrayDescriptor aray_descriptior = ArrayDescriptor.createDescriptor(typeName, connection);
        java.sql.Array sql_array = new oracle.sql.ARRAY(aray_descriptior, connection, list.toArray(new String[list.size()]));
        return sql_array;
    }

    @PostConstruct
    void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);

    }

    @Override
    public Optional<QueryDataResponse> queryDataEntry(QueryDataRequest request) throws SQLException {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " queryDataEntry Entry");
        long entryTime = System.currentTimeMillis();

        QueryDataResponse response = new QueryDataResponse();

        try {

            String pclvlareaForSQL = String.join(",", request.getPclvlarea()
                    .stream()
                    .map(name -> ("'" + name + "'"))
                    .collect(Collectors.toList()));

            SqlParameterSource incomp = new MapSqlParameterSource()
                    .addValue("pdquerydt1", request.getPdquerydt1() == null ? null : request.getPdquerydt1())
                    .addValue("pcgen", request.getPcgen() == null ? null : request.getPcgen())
                    .addValue("pcawbno", request.getPcawbno() == null ? null : request.getPcawbno())
                    .addValue("mccollempcd", request.getMccollempcd() == null ? null : request.getMccollempcd())
                    .addValue("mccollloccd", request.getMccollloccd() == null ? null : request.getMccollloccd())
                    .addValue("mcdcrrole", request.getMcdcrrole() == null ? null : request.getMcdcrrole())
                    .addValue("pregion", request.getPregion() == null ? null : request.getPregion())
                    .addValue("pclvlarea", request.getPclvlarea() == null ? null : " (" + pclvlareaForSQL + ") ");


            String[] params = incomp.getParameterNames();
            Arrays.stream(params).forEach(x -> System.out.println(incomp.getValue(x)));

            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2").withProcedureName("query_data_entry");

            Map res = spcall.callSProc(simpleJdbcCall, incomp, "query_data_entry");
            logger.info("RESPONSE FROM DB: " + res);
            if (res != null) {
                List<LinkedCaseInsensitiveMap<Object>> rawResuts = (List<LinkedCaseInsensitiveMap<Object>>) res.get("coll_detl");
                Iterator<LinkedCaseInsensitiveMap<Object>> iterator = rawResuts.stream().iterator();
                while (iterator.hasNext()) {
                    Map<String, String> aRecord = new HashMap<>();
                    LinkedCaseInsensitiveMap<Object> arow = iterator.next();
                    for (Map.Entry<String, Object> entry : arow.entrySet()) {
                        if (entry.getValue() != null) {
                            aRecord.put(entry.getKey(), entry.getValue().toString());
                        } else {
                            aRecord.put(entry.getKey(), null);
                        }
                    }
                    response.getColl_detl().add(aRecord);
                }
                List retstat = (ArrayList) res.get("retstat");
                response.setRetstat(retstat);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("IMPL==> QueryDataResponse==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " queryDataEntry Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);
    }

    @Override
    public Optional<UpdCollAmtResponse> updCollAmt(UpdCollAmtRequest updCollAmtRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " updCollAmt Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource().addValue("pcawbno", updCollAmtRequest.getAwbNo())
                .addValue("pncolamt", updCollAmtRequest.getColAmt())
                .addValue("submit", updCollAmtRequest.getSubmit())
                .addValue("pupdate", updCollAmtRequest.getUpdate())
                .addValue("poldncolamt", updCollAmtRequest.getOldnColAmt());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("UpdCollAmt");

        logger.info("IMPL==> updCollAmt==> simpleJdbcCall=" + simpleJdbcCall);

        Map res = (Map) spcall.callSProc(simpleJdbcCall, inawb, "UpdCollAmt");

        UpdCollAmtResponse response = new UpdCollAmtResponse();
        logger.info("IMPL==> UpdCollAmtResponse ==> res={}", res);
        if (res != null) {
//            logger.info("IMPL==> UpdCollAmtResponse ==> CUR_RETTEXT res=" + res.get("RETTEXT"));
            response = updCollAmtResponseProcessor.processUpdCollAmtResponse(res);
        }
        logger.info("IMPL==> UpdCollAmtResponse==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " updCollAmt Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));

        return Optional.of(response);
    }
    
    @Override
    public Optional<getShortResponse> ShortDetails(ShortRequest shortRequest) {
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " ShortDetails Entry");
        long entryTime = System.currentTimeMillis();
        SqlParameterSource inawb = new MapSqlParameterSource()
        		.addValue("pcdetailsrno", shortRequest.getPcdetailsrNo());
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("getShort");

        logger.info("IMPL==> ShortDetails==> simpleJdbcCall=" + simpleJdbcCall);
        Map res = (Map) spcall.callSProc(simpleJdbcCall, inawb, "getShort");
        getShortResponse response = new getShortResponse();
        logger.info("IMPL==> ShortDetailsResponse ==> res={}", res);
        if (res != null) {
            response = updCollAmtResponseProcessor.processgetShortResponse(res);
        }
        logger.info("IMPL==> getShortResponse==>response=" + response);
        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " ShortDetails Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);
    }


    @Override
    public Optional<OldCurrAmtResponse> oldCurrAmt(OldCurrAmtRequest oldCurrAmtRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " updCollAmt Entry");
        long entryTime = System.currentTimeMillis();
        SqlParameterSource inawb = new MapSqlParameterSource()
                .addValue("mccolltype", oldCurrAmtRequest.getMcolltype())
                .addValue("mcpaytype", oldCurrAmtRequest.getMpaytype())
                .addValue("mccollempcd", oldCurrAmtRequest.getMcollempcd())
                .addValue("pctranssrno", oldCurrAmtRequest.getPtranssrno())
                .addValue("pcserialno", oldCurrAmtRequest.getPserialno());
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("getOldCurrAmt");
        logger.info("IMPL==> updCollAmt==> simpleJdbcCall=" + simpleJdbcCall);
        Map res = (Map) spcall.callSProc(simpleJdbcCall, inawb, "UpdCollAmt");
        OldCurrAmtResponse response = new OldCurrAmtResponse();
        logger.info("IMPL==> oldCurrAmtResponse ==> res={}", res);
        if (res != null) {
            logger.info("IMPL==> oldCurrAmtResponse ==> mncollamtold res=" + res.get("mncollamtold"));
            logger.info("IMPL==> oldCurrAmtResponse ==> mntotamtcurr res=" + res.get("mntotamtcurr"));
            response = oldCurrAmtResponseProcessor.processOldCurrAmtResponse(res);
        }
        logger.info("IMPL==> oldCurrAmtResponse==>response=" + response);
        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " getOldCurrAmt Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);
    }
    
    
    @Override
    public Optional<SessionflagResponse> GetSessionflag(SessionflagRequest sessionflagRequest) {
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " GetSessionflag Entry");
        long entryTime = System.currentTimeMillis();
        SqlParameterSource inawb = new MapSqlParameterSource()
                .addValue("mcemplcode", sessionflagRequest.getMcemplcode())
                .addValue("mcpassword", sessionflagRequest.getMcpassword())
                .addValue("pcscrcd", sessionflagRequest.getPcscrcd())
                .addValue("mclevel", sessionflagRequest.getMclevel())
                .addValue("msubmit", sessionflagRequest.getMsubmit());
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("createsession");
        logger.info("IMPL==> GetSessionflag==> simpleJdbcCall=" + simpleJdbcCall);
        Map res = (Map) spcall.callSProc(simpleJdbcCall, inawb, "createsession");
        SessionflagResponse response = new SessionflagResponse();
        logger.info("IMPL==> GetSessionflag ==> res={}", res);
        if (res != null) {
            logger.info("IMPL==> GetSessionflag ==> retval res=" + res.get("retval"));
            String retval1 = (String) res.get("retval");
            String mcscrcd1 = (String) res.get("mcscrcd");
            response.setRetval(retval1);
            response.setMcscrcd(mcscrcd1);
        }
        logger.info("IMPL==> GetSessionflag==>response=" + response);
        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " GetSessionflag Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);
    }
}
