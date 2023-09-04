package com.org.entportal.service.impl;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.request.*;
import com.org.entportal.response.*;
import com.org.entportal.response.createlogin.processor.CreateLoginResponseProcessor;
import com.org.entportal.response.multiareauser.processor.MultiAreaUserLinkResponseProcessor;
import com.org.entportal.response.preremittance.processor.PreRemittanceActivityResponsePocessor;
import com.org.entportal.response.useraccess.processor.UserAccessResponseProcessor;
import com.org.entportal.service.UserService;
import com.org.entportal.spcall.StoredProcedureCall;
import oracle.jdbc.driver.OracleConnection;
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

import javax.annotation.PostConstruct;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StoredProcedureCall spcall;

    @Autowired
    private UserAccessResponseProcessor userAccessResponseProcessor;

    @Autowired
    private CreateLoginResponseProcessor createLoginResponseProcessor;

    @Autowired
    private MultiAreaUserLinkResponseProcessor multiAreaUserLinkResponseProcessor;

    @Autowired
    private PreRemittanceActivityResponsePocessor preRemittanceActivityResponsePocessor;

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
    public Optional<UserAccessResponse> displayUserAccess(UserAccessRequest userAccessRequest) {
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " displayUserAccess Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource()
                .addValue("mccloccode", userAccessRequest.getCclocCode())
                .addValue("mcdcrrole", userAccessRequest.getCdcrRole())
                .addValue("mcemplcode", userAccessRequest.getMcemplcode());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("display_user_access");

        logger.info("IMPL==> displayUserAccess ==> simpleJdbcCall=" + simpleJdbcCall);

        Map res = spcall.callSProc(simpleJdbcCall, inawb, "display_user_access");

        UserAccessResponse response = new UserAccessResponse();
        logger.info("IMPL==> UserAccessResponse==> res={}", res);
        if (res != null) {
            logger.info("IMPL==> UserAccessResponse==> CUR_CUR res=" + res.get("CUR"));
            logger.info("IMPL==> UserAccessResponse==> CUR_CUR1 res=" + res.get("CUR1"));
            logger.info("IMPL==> UserAccessResponse==> CUR_CUR2 res=" + res.get("CUR2"));
            response = userAccessResponseProcessor.processUserAccessResponse(res);
        }
        logger.info("IMPL==> UserAccessResponse==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " displayUserAccess Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + (exitTime - entryTime));
        return Optional.of(response);
    }

    @Override
    public Optional<CreateUserRespone> createUser(CreateUserRequest request) throws SQLException {
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " createUser Entry");
        long entryTime = System.currentTimeMillis();

        CreateUserRespone response = new CreateUserRespone();

        Connection oracleConnection = null;

        String retText = null;

        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            oracleConnection = connection.isWrapperFor(OracleConnection.class) ? connection.unwrap(OracleConnection.class) : connection;

            CallableStatement statement = oracleConnection.prepareCall("{call WEBDCR2.save_createuser(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            java.sql.Array pcemplcode_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcemplcode(), oracleConnection);
            statement.setArray(1, pcemplcode_sql_array);
            java.sql.Array pcrole_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcrole(), oracleConnection);
            statement.setArray(2, pcrole_sql_array);
            java.sql.Array pdexpitydt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPdexpitydt(), oracleConnection);
            statement.setArray(3, pdexpitydt_sql_array);
            java.sql.Array pclocaccess_sql_array = GetSqlArray("T_TABLE_VARCHAR2_800", request.getPclocaccess(), oracleConnection);
            statement.setArray(4, pclocaccess_sql_array);
            statement.setString(5, request.getPcrole1());
            statement.setString(6, request.getPcrole2());
            statement.setString(7, request.getPcrole3());
            statement.setString(8, request.getPcrole4());
            statement.setString(9, request.getPcrole5());
            statement.setString(10, request.getPcrole6());
            statement.setString(11, request.getPcrole7());
            statement.setString(12, request.getPcrole8());
            statement.setString(13, request.getPcrole9());
            statement.setString(14, request.getPcrolea());
            statement.setString(15, request.getPcroleb());
            statement.setString(16, request.getPcrolec());
            statement.setString(17, request.getPcroled());
            statement.setString(18, request.getPcrolee());
            statement.setString(19, request.getPclose());
            statement.setString(20, request.getPsubmit());
            statement.setString(21, request.getMclogempcd());
            statement.registerOutParameter(22, Types.VARCHAR);

            statement.execute();

            retText = statement.getString(22);

            response.setRettext(retText);

            statement.close();
        } catch (SQLException ex) {
            logger.info("IMPL==> testSave ==> exception={}", ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

        logger.info("IMPL==> CreateUserRespone==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " createUser Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + (exitTime - entryTime));
        return Optional.of(response);
    }

    @Override
    public Optional<CreateLoginResponse> createLogin(CreateLoginRequest createLoginRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " createLogin Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource().addValue("pcemplcode", createLoginRequest.getEmplCode());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("createlogin");

        logger.info("IMPL==> createLogin ==> simpleJdbcCall=" + simpleJdbcCall);

        Map res = spcall.callSProc(simpleJdbcCall, inawb, "createlogin");

        CreateLoginResponse response = new CreateLoginResponse();
        logger.info("IMPL==> CreateLoginResponse ==> res={}", res);
        if (res != null) {
            logger.info("IMPL==> CreateLoginResponse ==> c1 res=" + res.get("C1"));
            logger.info("IMPL==> CreateLoginResponse ==> c2 res=" + res.get("C2"));
            logger.info("IMPL==> CreateLoginResponse ==> Role res=" + res.get("PCROLE"));
            logger.info("IMPL==> CreateLoginResponse ==> LV_LOCACCESS res=" + res.get("LV_LOCACCESS"));

            response = createLoginResponseProcessor.processCreateLoginResponse(res);
        }
        logger.info("IMPL==> CreateLoginResponse ==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " createLogin Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + (exitTime - entryTime));
        return Optional.of(response);

    }

    @Override
    public Optional<MultiAreaUserLinkResponse> multiAreaUserLink(MultiAreaUserLinkRequest multiAreaUserLinkRequest) {
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " multiAreaUserLink Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource()
                .addValue("mcemplcode", multiAreaUserLinkRequest.getEmplcode())
                .addValue("mcarea", multiAreaUserLinkRequest.getArea())
                .addValue("mcchk", multiAreaUserLinkRequest.getChk())
                .addValue("mcsubmit", multiAreaUserLinkRequest.getSubmit())
                .addValue("mcreset", multiAreaUserLinkRequest.getReset());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("multiareauserlink");

        logger.info("IMPL==> multiAreaUserLink ==> simpleJdbcCall=" + simpleJdbcCall);

        Map res = spcall.callSProc(simpleJdbcCall, inawb, "multiareauserlink");

        MultiAreaUserLinkResponse response = new MultiAreaUserLinkResponse();
        logger.info("IMPL==> MultiAreaUserLinkResponse==> res={}", res);
        if (res != null) {
            logger.info("IMPL==> MultiAreaUserLinkResponse==> CUR_C1 res=" + res.get("C1"));
            logger.info("IMPL==> MultiAreaUserLinkResponse==> retText res=" + res.get("RETTEXT"));
            response = multiAreaUserLinkResponseProcessor.processMultiAreaUserLinkResponse(res);
        }
        logger.info("IMPL==> MultiAreaUserLinkResponse ==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " multiAreaUserLink Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + (exitTime - entryTime));
        return Optional.of(response);
    }

    @Override
    public Optional<PreRemittanceActivityResponse> preRemittanceActivity(
            PreRemittanceActivityRequest preRemittanceActivityRequest) {
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " preRemittanceActivity Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource()
                .addValue("pcdate", preRemittanceActivityRequest.getDate())
                .addValue("pcawbno", preRemittanceActivityRequest.getAwbno())
                .addValue("mcsaveflg", preRemittanceActivityRequest.getSaveflg())
                .addValue("submit", preRemittanceActivityRequest.getSubmit())
                .addValue("process", preRemittanceActivityRequest.getProcess())
                .addValue("data", preRemittanceActivityRequest.getData())
                .addValue("pcdate1", preRemittanceActivityRequest.getDate1())
                .addValue("pcdate2", preRemittanceActivityRequest.getDate2());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("prebanktrf");

        logger.info("IMPL==> preRemittanceActivity ==> simpleJdbcCall=" + simpleJdbcCall);

        Map res = spcall.callSProc(simpleJdbcCall, inawb, "prebanktrf");

        PreRemittanceActivityResponse response = new PreRemittanceActivityResponse();
        logger.info("IMPL==> PreRemittanceActivityResponse ==> res={}", res);
        if (res != null) {
            logger.info("IMPL==> PreRemittanceActivityResponse ==> CUR_C1 res=" + res.get("C1"));
            logger.info("IMPL==> PreRemittanceActivityResponse ==> CUR_C2 res=" + res.get("C2"));
            logger.info("IMPL==> PreRemittanceActivityResponse ==> CUR_CUR_ERR res=" + res.get("CUR_ERR"));
            response = preRemittanceActivityResponsePocessor.processPreRemittanceActivityResponse(res);
        }
        logger.info("IMPL==> PreRemittanceActivityResponse ==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " preRemittanceActivity Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + (exitTime - entryTime));
        return Optional.of(response);

    }
}
