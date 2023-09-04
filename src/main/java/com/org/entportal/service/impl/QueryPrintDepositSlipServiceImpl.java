package com.org.entportal.service.impl;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.request.*;
import com.org.entportal.response.*;
import com.org.entportal.response.queryprintdepositslip.processor.FindSerialPaySlipResponseProcessor;
import com.org.entportal.response.queryprintdepositslip.processor.PrintPaySlipResponseProcessor;
import com.org.entportal.response.queryprintdepositslip.processor.QueryPaySlipResponseProcessor;
import com.org.entportal.service.QueryPrintDepositSlipService;
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
public class QueryPrintDepositSlipServiceImpl implements QueryPrintDepositSlipService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StoredProcedureCall spcall;

    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    private PrintPaySlipResponseProcessor printPaySlipProcessor;
    @Autowired
    private QueryPaySlipResponseProcessor queryPaySlipProcessor;
    @Autowired
    private FindSerialPaySlipResponseProcessor findSerialPaySlipProcessor;

    private static java.sql.Array GetSqlArray(String typeName, java.util.List<String> list, Connection connection) throws SQLException {
        ArrayDescriptor aray_descriptior = ArrayDescriptor.createDescriptor(typeName, connection);
        java.sql.Array sql_array = new oracle.sql.ARRAY(aray_descriptior, connection, list.toArray(new String[list.size()]));
        return sql_array;
    }

    @Override
    public Optional<PrintPaySlipResponse> printPaySlip(PrintPaySlipRequest printPaySlipRequest) {
        PrintPaySlipResponse response = null;

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " printPaySlip Entrys");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pcdepositsrno", printPaySlipRequest.getPcdepositsrno());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2").withProcedureName("print_payslip");

        Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "print_payslip");

        if (res != null) {
            response = printPaySlipProcessor.printPaySlip(res);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " printPaySlip Exit");
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }

    @Override
    public Optional<QueryPaySlipResponse> queryPaySlip(QueryPaySlipRequest queryPaySlipRequest) {
        QueryPaySlipResponse response = null;

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " queryPaySlip Entrys");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("mccollloccd", queryPaySlipRequest.getMccollloccd())
                .addValue("pcdepositsrno", queryPaySlipRequest.getPcdepositsrno())
                .addValue("pdquerydt1", queryPaySlipRequest.getPdquerydt1());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2").withProcedureName("query_payslip");

        Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "query_payslip");

        if (res != null) {
            response = queryPaySlipProcessor.queryPaySlip(res);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " queryPaySlip Exit");
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);
    }

    @Override
    public Optional<SaveDataPaySlipResponse> saveDataPaySlip(SaveDataPaySlipRequest request) throws SQLException {
        SaveDataPaySlipResponse response = new SaveDataPaySlipResponse();

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " saveDataPaySlip Entrys");

        Connection oracleConnection = null;

        String retText = null;

        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            oracleConnection = connection.isWrapperFor(OracleConnection.class) ? connection.unwrap(OracleConnection.class) : connection;

            CallableStatement statement = oracleConnection.prepareCall("{call WEBDCR2.save_data_payslip(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            statement.setString(1, request.getPcarea());
            statement.setString(2, request.getPnentry_no());
            statement.setString(3, request.getPctranssrno());
            statement.setString(4, request.getPcdetailsrno());
            statement.setString(5, request.getPdcollrcptdt());
            statement.setString(6, request.getPcdepositsrno());
            statement.setString(7, request.getPcserialno());
            statement.setString(8, request.getPcpaytype());
            statement.setString(9, request.getPccolltype());
            statement.setString(10, request.getPccollrcptno());
            statement.setString(11, request.getPcawbno());
            statement.setString(12, request.getPcprodcode());
            statement.setString(13, request.getPcorgscrcd());
            statement.setString(14, request.getPninvamt());
            statement.setString(15, request.getPnoutstandamt());
            statement.setString(16, request.getPncollamt());
            statement.setString(17, request.getPntdsamt());
            statement.setString(18, request.getPcchequeno());
            statement.setString(19, request.getPdcheqdate());
            statement.setString(20, request.getPcbanknm());
            statement.setString(21, request.getPcbankbranchnm());
            statement.setString(22, request.getPccollloccd());
            statement.setString(23, request.getPccloccode());
            statement.setString(24, request.getPccollempcd());
            statement.setString(25, request.getPcremarks());
            statement.setString(26, request.getOk());
            statement.setString(27, request.getPncrdramt());
            statement.setString(28, request.getPctranstype());
            java.sql.Array pcreasoncd_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcreasoncd(), oracleConnection);
            statement.setArray(29, pcreasoncd_sql_array);
            java.sql.Array pnshortcoll_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPnshortcoll(), oracleConnection);
            statement.setArray(30, pnshortcoll_sql_array);

            statement.registerOutParameter(31, Types.VARCHAR);

            statement.execute();

            retText = statement.getString(31);

            response.setRetText(retText);

            statement.close();
        } catch (SQLException ex) {
            logger.info("IMPL==> testSave ==> exception={}", ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }


        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " saveDataPaySlip Exit");
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);
    }

    @Override
    public Optional<FindSerialPaySlipResponse> findSerialPaySlip(FindSerialPaySlipRequest findSerialPaySlipRequest) {
        FindSerialPaySlipResponse response = null;

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " findSerialPaySlip Entrys");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pcawbno", findSerialPaySlipRequest.getPcawbno())
                .addValue("pcbanknm", findSerialPaySlipRequest.getPcbanknm())
                .addValue("pcbillno", findSerialPaySlipRequest.getPcbillno())
                .addValue("pcchequeno", findSerialPaySlipRequest.getPcchequeno())
                .addValue("pccolltype", findSerialPaySlipRequest.getPccolltype())
                .addValue("pcorgscrcd", findSerialPaySlipRequest.getPcorgscrcd())
                .addValue("pcpaytype", findSerialPaySlipRequest.getPcpaytype())
                .addValue("pcprodcode", findSerialPaySlipRequest.getPcprodcode())
                .addValue("pdbilldt", findSerialPaySlipRequest.getPdbilldt())
                .addValue("pdcheqdate", findSerialPaySlipRequest.getPdcheqdate())
                .addValue("pncollamt", findSerialPaySlipRequest.getPncollamt())
                .addValue("pnoutstandamt", findSerialPaySlipRequest.getPnoutstandamt());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName("WEBDCR2").withProcedureName("find_serial_payslip");

        Map<String, Object> res = (Map<String, Object>) spcall.callSProc(simpleJdbcCall, params, "find_serial_payslip");

        if (res != null) {
            response = findSerialPaySlipProcessor.findSerialPaySlip(res);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " findSerialPaySlip Exit");
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }

    @Override
    public Optional<SaveActualDataPaySlipResponse> saveActualDataPaySlip(
            SaveActualDataPaySlipRequest request) throws SQLException {
        SaveActualDataPaySlipResponse response = new SaveActualDataPaySlipResponse();

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " saveActualDataPaySlip Entrys");

        Connection oracleConnection = null;

        String retText = null;

        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            oracleConnection = connection.isWrapperFor(OracleConnection.class) ? connection.unwrap(OracleConnection.class) : connection;

            CallableStatement statement = oracleConnection.prepareCall("{call WEBDCR2.save_actual_data_payslip(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            statement.setString(1, request.getPccollloccd());
            statement.setString(2, request.getPccollempcd());
            statement.setString(3, request.getPccloccode());
            statement.setString(4, request.getPdpayslipdt());
            statement.setString(5, request.getPcpayslipno());
            statement.setString(6, request.getPntotaldepoamt());
            statement.setString(7, request.getPcbankdesc());
            statement.setString(8, request.getPcaccno());
            statement.setString(9, request.getPtotamtcurr());
            statement.setString(10, request.getPmccolltype());
            statement.setString(11, request.getPmcpaytype());
            java.sql.Array pcawbno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcawbno(), oracleConnection);
            statement.setArray(12, pcawbno_sql_array);
            java.sql.Array pcprodcode_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcprodcode(), oracleConnection);
            statement.setArray(13, pcprodcode_sql_array);
            java.sql.Array pcorgscrcd_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcorgscrcd(), oracleConnection);
            statement.setArray(14, pcorgscrcd_sql_array);
            java.sql.Array pncollamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPncollamt(), oracleConnection);
            statement.setArray(15, pncollamt_sql_array);
            java.sql.Array pcserialno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcserialno(), oracleConnection);
            statement.setArray(16, pcserialno_sql_array);
            statement.setString(17, request.getSubmit());
            statement.registerOutParameter(18, Types.VARCHAR);

            statement.execute();

            retText = statement.getString(18);

            response.setRetText(retText);

            statement.close();
        } catch (SQLException ex) {
            logger.info("IMPL==> testSave ==> exception={}", ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " saveActualDataPaySlip Exit");
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }

}
