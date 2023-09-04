package com.org.entportal.service.impl;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.request.ApproveCollectionRequest;
import com.org.entportal.request.SaveApprovalRequest;
import com.org.entportal.request.SaveDeleteRequest;
import com.org.entportal.response.ApproveCollectionResponse;
import com.org.entportal.response.SaveApprovalResponse;
import com.org.entportal.response.SaveDeleteResponse;
import com.org.entportal.response.cashdepositapproval.processor.ApproveCollectionResponseProcessor;
import com.org.entportal.response.cashdepositapproval.processor.SaveApprovalResponseProcessor;
import com.org.entportal.response.cashdepositapproval.processor.SaveDeleteResponseProcessor;
import com.org.entportal.service.CashDepositApprovalService;
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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.Optional;

@Service
public class CashDepositApprovalServiceImpl implements CashDepositApprovalService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StoredProcedureCall spcall;

    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    private SaveApprovalResponseProcessor saveApprovalProcessor;
    @Autowired
    private ApproveCollectionResponseProcessor approveCollectionProcessor;
    @Autowired
    private SaveDeleteResponseProcessor saveDeleteProcessor;

    private static java.sql.Array GetSqlArray(String typeName, java.util.List<String> list, Connection connection) throws SQLException {
        ArrayDescriptor aray_descriptior = ArrayDescriptor.createDescriptor(typeName, connection);
        java.sql.Array sql_array = new oracle.sql.ARRAY(aray_descriptior, connection, list.toArray(new String[list.size()]));
        return sql_array;
    }

    @Override
    public Optional<SaveApprovalResponse> saveApproval(SaveApprovalRequest request) throws SQLException {
        SaveApprovalResponse response = new SaveApprovalResponse();

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " saveApproval Entrys");

        Connection oracleConnection = null;

        String retText = null;

        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            oracleConnection = connection.isWrapperFor(OracleConnection.class) ? connection.unwrap(OracleConnection.class) : connection;

            CallableStatement statement = oracleConnection.prepareCall("{call WEBDCR2.save_approval(?,?,?,?,?,?,?,?)}");

            java.sql.Array pcpayslipno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_100", request.getPcpayslipno(), oracleConnection);
            statement.setArray(1, pcpayslipno_sql_array);
            java.sql.Array pdpayslipdt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPdpayslipdt(), oracleConnection);
            statement.setArray(2, pdpayslipdt_sql_array);
            java.sql.Array pcchk_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcchk(), oracleConnection);
            statement.setArray(3, pcchk_sql_array);
            statement.setString(4, request.getAllchk());
            statement.setString(5, request.getPcapprovedby());
            statement.setString(6, request.getMccollloccd());
            statement.setString(7, request.getMccollempcd());

            statement.registerOutParameter(8, Types.VARCHAR);

            statement.execute();

            retText = statement.getString(8);

            response.setRetText(retText);

            statement.close();
        } catch (SQLException ex) {
            logger.info("IMPL==> testSave ==> exception={}", ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " saveApproval Exit");
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }

    @Override
    public Optional<ApproveCollectionResponse> approveCollection(ApproveCollectionRequest approveCollectionRequest) {
        ApproveCollectionResponse response = null;

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " approveCollection Entrys");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pcolloc", approveCollectionRequest.getPcolloc())
                .addValue("pdcolldt", approveCollectionRequest.getPdcolldt())
                .addValue("psubmit", approveCollectionRequest.getPsubmit())
                .addValue("mccollloccd", approveCollectionRequest.getMccollloccd())
                .addValue("mccollempcd", approveCollectionRequest.getMccollempcd());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2").withProcedureName("approve_collection");

        Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "approve_collection");

        if (res != null) {
            response = approveCollectionProcessor.approveCollection(res);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " approveCollection Exit");
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);
    }

    @Override
    public Optional<SaveDeleteResponse> saveDelete(SaveDeleteRequest saveDeleteRequest) {
        SaveDeleteResponse response = null;

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " saveDelete Entrys");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pcdate", saveDeleteRequest.getPcdate())
                .addValue("pcloc", saveDeleteRequest.getPcloc())
                .addValue("pcpayslipno", saveDeleteRequest.getPcpayslipno());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2").withProcedureName("save_delete");

        Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "save_delete");

        if (res != null) {
            response = saveDeleteProcessor.saveDelete(res);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " saveDelete Exit");
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);
    }
}
