package com.org.entportal.service.impl;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.request.*;
import com.org.entportal.response.*;
import com.org.entportal.response.addreasoninv.processor.AddReasonInvBulkEntResponseProcessor;
import com.org.entportal.response.bulksave.processor.DoBulkSaveResponseProcessor;
import com.org.entportal.response.bulksaveinv.processor.DoBulkSaveInvResponseProcessor;
import com.org.entportal.response.bulkupload.processor.BulkUploadProcessor;
import com.org.entportal.response.entryscreen.processor.EntryScreenResponseProcessor;
import com.org.entportal.response.entryscreeninv.processor.EntryScreenInvReponseProcessor;
import com.org.entportal.response.uploadstatus.UploadStatusResponseProcessor;
import com.org.entportal.service.BulkService;
import com.org.entportal.spcall.StoredProcedureCall;
import oracle.jdbc.driver.OracleConnection;
import oracle.sql.ArrayDescriptor;
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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Service
public class BulkServiceImpl implements BulkService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StoredProcedureCall spcall;

    @Autowired
    private BulkUploadProcessor bulkUploadProcessor;

    @Autowired
    private UploadStatusResponseProcessor uploadStatusResponseProcessor;

    @Autowired
    private EntryScreenResponseProcessor entryScreenResponseProcessor;

    @Autowired
    private DoBulkSaveResponseProcessor doBulkSaveResponseProcessor;

    @Autowired
    private EntryScreenInvReponseProcessor entryScreenInvReponseProcessor;

    @Autowired
    private DoBulkSaveInvResponseProcessor doBulkSaveInvResponseProcessor;

    @Autowired
    private AddReasonInvBulkEntResponseProcessor addReasonInvBulkEntResponseProcessor;

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
    public void getValFromUpload(GetValFromUploadRequest getValFromUploadRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " getValFromUpload Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource().addValue("mcawbno", getValFromUploadRequest.getAwbno())
                .addValue("mncollamt", getValFromUploadRequest.getCollamt())
                .addValue("mcremarks", getValFromUploadRequest.getRemarks())
                .addValue("mcchequeno", getValFromUploadRequest.getChequeno())
                .addValue("mdcheqdate", getValFromUploadRequest.getCheqdate())
                .addValue("mcemplcode", getValFromUploadRequest.getEmplcode())
                .addValue("mcscrcd", getValFromUploadRequest.getScrcd())
                .addValue("pdcollrcptdt", getValFromUploadRequest.getCollrcptdt())
                .addValue("pccolltype", getValFromUploadRequest.getColltype())
                .addValue("pcpaytype", getValFromUploadRequest.getPaytype());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("getvalfromupload");

        logger.info("IMPL==> getValFromUpload==> simpleJdbcCall=" + simpleJdbcCall);

        spcall.callSProc(simpleJdbcCall, inawb, "getvalfromupload");

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " getValFromUpload Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));

    }

    @Override
    public Optional<BulkUploadResponse> bulkUpload(BulkUploadRequest bulkUploadRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " bulkUpload Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource().addValue("mccollloccd", bulkUploadRequest.getMccollloccd());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("collection_bulk_upload");

        logger.info("IMPL==> bulkUpload==> simpleJdbcCall=" + simpleJdbcCall);

        Map res = spcall.callSProc(simpleJdbcCall, inawb, "collection_bulk_upload");

        BulkUploadResponse response = new BulkUploadResponse();
        logger.info("IMPL==> BulkUploadResponse ==> res={}", res);

        if (res != null) {
            response = bulkUploadProcessor.processBulkUploadResponse(res);
        }
        logger.info("IMPL==> BulkUploadResponse ==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " bulkUpload Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }

    @Override
    public Optional<UploadStatusResponse> uploadStatus() {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " uploadStatus Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource();

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("upload_status").returningResultSet("uploadStatusResponse",
                        BeanPropertyRowMapper.newInstance(UploadStatusResponse.class));

        logger.info("IMPL==> uploadStatus ==> simpleJdbcCall=" + simpleJdbcCall);

        Map res = (Map) spcall.callSProc(simpleJdbcCall, inawb, "upload_status");

        UploadStatusResponse response = new UploadStatusResponse();
        logger.info("IMPL==> BankResponse ==> res={}", res);
        if (res != null) {
            logger.info("IMPL==> BankResponse ==> c1 res=" + res.get("C1"));
            logger.info("IMPL==> BankResponse ==> c2 res=" + res.get("C2"));
            response = uploadStatusResponseProcessor.processUploadStatusResponse(res);
        }
        logger.info("IMPL==> UploadStatusResponse ==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " uploadStatus Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }

    @Override
    public Optional<EntryScreenResponse> entryScreen(EntryScreenRequest entryScreenRequest) {
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " entryScreen Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource()
                .addValue("pcemplcode", entryScreenRequest.getEmplcode())
                .addValue("pcawbno", entryScreenRequest.getAwbno())
                .addValue("pdfromdt", entryScreenRequest.getFromdt())
                .addValue("pdtodt", entryScreenRequest.getTodt())
                .addValue("pgsaarea", entryScreenRequest.getSaarea())
                .addValue("pgsacode", entryScreenRequest.getSacode())
                .addValue("psubmit", entryScreenRequest.getSubmit());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("entryscreen");

        logger.info("IMPL==> uploadStatus ==> simpleJdbcCall=" + simpleJdbcCall);

        Map res = (Map) spcall.callSProc(simpleJdbcCall, inawb, "entryscreen");

        EntryScreenResponse response = new EntryScreenResponse();
        logger.info("IMPL==> EntryScreenResponse ==> res={}", res);

        if (res != null) {
            logger.info("IMPL==> EntryScreenResponse ==> CUR_PCCOLLMD res=" + res.get("PCCOLLMD"));
            logger.info("IMPL==> EntryScreenResponse ==> CUR_OUTSTND1 res=" + res.get("OUTSTND1"));
            logger.info("IMPL==> EntryScreenResponse ==> CUR_OUTSTND res=" + res.get("OUTSTND"));
            response = entryScreenResponseProcessor.processEntryScreenResponse(res);
        }
        logger.info("IMPL==> EntryScreenResponse ==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " entryScreen Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);
    }

    @Override
    public Optional<DoBulkSaveResponse> doBulkSave(DoBulkSaveRequest request) throws SQLException {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " doBulkSave Entry");
        long entryTime = System.currentTimeMillis();

        DoBulkSaveResponse response = new DoBulkSaveResponse();
        Connection oracleConnection = null;
        String retText = null;

        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            oracleConnection = connection.isWrapperFor(OracleConnection.class)
                    ? connection.unwrap(OracleConnection.class)
                    : connection;

            CallableStatement statement = oracleConnection.prepareCall("{call WEBDCR2.dobulksave(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            java.sql.Array pcawbno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcawbno(), oracleConnection);
            statement.setArray(1, pcawbno_sql_array);
            java.sql.Array pdpudeldt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPdpudeldt(), oracleConnection);
            statement.setArray(2, pdpudeldt_sql_array);
            java.sql.Array pcpaytype_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcpaytype(), oracleConnection);
            statement.setArray(3, pcpaytype_sql_array);
            java.sql.Array pncollval_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPncollval(), oracleConnection);
            statement.setArray(4, pncollval_sql_array);
            java.sql.Array pncollamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPncollamt(), oracleConnection);
            statement.setArray(5, pncollamt_sql_array);
            java.sql.Array pntdsamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPntdsamt(), oracleConnection);
            statement.setArray(6, pntdsamt_sql_array);
            java.sql.Array pccashmemno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPccashmemno(), oracleConnection);
            statement.setArray(7, pccashmemno_sql_array);
            java.sql.Array pccollmd_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPccollmd(), oracleConnection);
            statement.setArray(8, pccollmd_sql_array);
            java.sql.Array pcchk_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcchk(), oracleConnection);
            statement.setArray(9, pcchk_sql_array);
            java.sql.Array pcrmks_sql_array = GetSqlArray("T_TABLE_VARCHAR2_400", request.getPcrmks(), oracleConnection);
            statement.setArray(10, pcrmks_sql_array);
            java.sql.Array pcchkno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcchkno(), oracleConnection);
            statement.setArray(11, pcchkno_sql_array);
            java.sql.Array pdchkdt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPdchkdt(), oracleConnection);
            statement.setArray(12, pdchkdt_sql_array);
            java.sql.Array pcbnknm_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcbnknm(), oracleConnection);
            statement.setArray(13, pcbnknm_sql_array);
            java.sql.Array pcbrnnm_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcbrnnm(), oracleConnection);
            statement.setArray(14, pcbrnnm_sql_array);
            java.sql.Array pcprodcode_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcprodcode(), oracleConnection);
            statement.setArray(15, pcprodcode_sql_array);
            java.sql.Array pcorgscrcd_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcorgscrcd(), oracleConnection);
            statement.setArray(16, pcorgscrcd_sql_array);
            java.sql.Array pcoutamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcoutamt(), oracleConnection);
            statement.setArray(17, pcoutamt_sql_array);
            java.sql.Array pcchk1_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcchk1(), oracleConnection);
            statement.setArray(18, pcchk1_sql_array);
            java.sql.Array pccollval1_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPccollval1(), oracleConnection);
            statement.setArray(19, pccollval1_sql_array);
            statement.setString(20, request.getPcpudelemplcd());
            statement.setString(21, request.getPdcolldt());
            statement.setString(22, request.getAllchk());
            statement.setString(23, request.getPctranid());
            statement.setString(24, request.getPgsaarea());
            statement.setString(25, request.getPgsacode());
            statement.setString(26, request.getMccollloccd());
            statement.setString(27, request.getMccollempcd());
            statement.setString(28, request.getMccloccode());
            statement.setString(29, request.getMcdcrrole());
            statement.registerOutParameter(30, Types.VARCHAR);

            statement.execute();

            retText = statement.getString(30);

            response.setRetText(retText);

            statement.close();
        } catch (SQLException ex) {
            logger.info("IMPL==> doBulkSave ==> exception={}", ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
        logger.info("IMPL==> DoBulkSaveResponse ==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " doBulkSave Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }

    @Override
    public Optional<EntryScreenInvResponse> entryScreenInv(EntryScreenInvRequest entryScreenInvRequest) {
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " entryScreenInv Entry");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource inawb = new MapSqlParameterSource().addValue("pcarea", entryScreenInvRequest.getArea())
                .addValue("pccustcode", entryScreenInvRequest.getCustcode())
                .addValue("pinvoice", entryScreenInvRequest.getInvoice())
                .addValue("pdcolldt", entryScreenInvRequest.getColldt())
                .addValue("pccolltype", entryScreenInvRequest.getColltype())
                .addValue("pcpaytype", entryScreenInvRequest.getPaytype())
                .addValue("pcchqno", entryScreenInvRequest.getChqno())
                .addValue("pdchqdt", entryScreenInvRequest.getChqdt())
                .addValue("pcbanknm", entryScreenInvRequest.getBanknm())
                .addValue("pcbranchnm", entryScreenInvRequest.getBranchnm())
                .addValue("psubmit", entryScreenInvRequest.getSubmit());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("entryscreeninv");

        logger.info("IMPL==> uploadStatus ==> simpleJdbcCall=" + simpleJdbcCall);

        Map res = (Map) spcall.callSProc(simpleJdbcCall, inawb, "entryscreeninv");

        EntryScreenInvResponse response = new EntryScreenInvResponse();
        logger.info("IMPL==> EntryScreenInvResponse ==> res={}", res);

        if (res != null) {
            logger.info("IMPL==> EntryScreenInvResponse ==> CUR_PCPAUTYPE1 res=" + res.get("PCPAUTYPE1"));
            logger.info("IMPL==> EntryScreenInvResponse ==> CUR_PCCOLLTYPE1 res=" + res.get("PCCOLLTYPE1"));
            logger.info("IMPL==> EntryScreenInvResponse ==> CUR_OUTSTND res=" + res.get("OUTSTND"));
            response = entryScreenInvReponseProcessor.processEntryScreenInvResponse(res);
        }
        logger.info("IMPL==> EntryScreenInvResponse ==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " entryScreenInv Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }

    @Override
    public Optional<DoBulkSaveInvResponse> doBulkSaveInv(DoBulkSaveInvRequest request) throws SQLException {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " doBulkSaveInv Entry");
        long entryTime = System.currentTimeMillis();

        DoBulkSaveInvResponse response = new DoBulkSaveInvResponse();

        Connection oracleConnection = null;

        String retText = null;

        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            oracleConnection = connection.isWrapperFor(OracleConnection.class) ? connection.unwrap(OracleConnection.class) : connection;

            CallableStatement statement = oracleConnection.prepareCall("{call WEBDCR2.dobulksaveinv(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            java.sql.Array pcbillno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcbillno(), oracleConnection);
            statement.setArray(1, pcbillno_sql_array);
            java.sql.Array pdbilldt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPdbilldt(), oracleConnection);
            statement.setArray(2, pdbilldt_sql_array);
            java.sql.Array pcentryno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcentryno(), oracleConnection);
            statement.setArray(3, pcentryno_sql_array);
            java.sql.Array pninvamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPninvamt(), oracleConnection);
            statement.setArray(4, pninvamt_sql_array);
            java.sql.Array pnoutamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPnoutamt(), oracleConnection);
            statement.setArray(5, pnoutamt_sql_array);
            java.sql.Array pncollamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPncollamt(), oracleConnection);
            statement.setArray(6, pncollamt_sql_array);
            java.sql.Array pntdsamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPntdsamt(), oracleConnection);
            statement.setArray(7, pntdsamt_sql_array);
            java.sql.Array pccashmemno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPccashmemno(), oracleConnection);
            statement.setArray(8, pccashmemno_sql_array);
            java.sql.Array pcchk_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcchk(), oracleConnection);
            statement.setArray(9, pcchk_sql_array);
            java.sql.Array pcrmks_sql_array = GetSqlArray("T_TABLE_VARCHAR2_400", request.getPcrmks(), oracleConnection);
            statement.setArray(10, pcrmks_sql_array);
            java.sql.Array pctotshrtamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPctotshrtamt(), oracleConnection);
            statement.setArray(11, pctotshrtamt_sql_array);
            java.sql.Array pcchk1_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcchk1(), oracleConnection);
            statement.setArray(12, pcchk1_sql_array);
            statement.setString(13, request.getPcarea());
            statement.setString(14, request.getPccustcode());
            statement.setString(15, request.getPinvoice());
            statement.setString(16, request.getPdcolldt());
            statement.setString(17, request.getPccolltype());
            statement.setString(18, request.getPcpaytype());
            statement.setString(19, request.getPcchqno());
            statement.setString(20, request.getPdchqdt());
            statement.setString(21, request.getPcbanknm());
            statement.setString(22, request.getPcbranchnm());
            statement.setString(23, request.getMccollloccd());
            statement.setString(24, request.getMccollempcd());

            statement.setString(25, request.getMccloccode());
            statement.setString(26, request.getMcdcrrole());
            statement.registerOutParameter(27, Types.VARCHAR);

            statement.execute();

            retText = statement.getString(27);

            response.setRetText(retText);

            statement.close();
        } catch (SQLException ex) {
            logger.info("IMPL==> Dobulksaveinv ==> exception={}", ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
        logger.info("IMPL==> DoBulkSaveInvResponse ==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " doBulkSaveInv Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }

    @Override
    public Optional<AddReasonInvBulkEntResponse> addReasonInvBulkEnt(
            AddReasonInvBulkEntRequest addReasonInvBulkEntRequest) throws SQLException {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " addReasonInvBulkEnt Entry");
        long entryTime = System.currentTimeMillis();
        AddReasonInvBulkEntResponse response = new AddReasonInvBulkEntResponse();
        Connection oracleConnection = null;

        List<String> mcreasoncd = null;
        List<String> shorts = null;
        String retText = null;

        try {

            Connection connection = jdbcTemplate.getDataSource().getConnection();

            oracleConnection = connection.isWrapperFor(OracleConnection.class) ? connection.unwrap(OracleConnection.class) : connection;

            CallableStatement statement = oracleConnection.prepareCall("{call WEBDCR2.add_reason_invbulkent(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            //java.sql.Array
            statement.setString(1, addReasonInvBulkEntRequest.getEntryno());
            statement.setString(2, addReasonInvBulkEntRequest.getBillno());
            statement.setString(3, addReasonInvBulkEntRequest.getOutamt());
            statement.setString(4, addReasonInvBulkEntRequest.getCollamt());
            statement.setString(5, addReasonInvBulkEntRequest.getTdsamt());
            statement.setString(6, addReasonInvBulkEntRequest.getShortid());
            statement.setString(7, addReasonInvBulkEntRequest.getPcreasoncd());
            statement.setArray(8,
                    GetSqlArray("T_TABLE_VARCHAR2_50", addReasonInvBulkEntRequest.getMcreasoncd(), oracleConnection));
            statement.setArray(9,
                    GetSqlArray("T_TABLE_VARCHAR2_50", addReasonInvBulkEntRequest.getShortcoll(), oracleConnection));
            statement.setString(10, addReasonInvBulkEntRequest.getSubmit());
            statement.registerOutParameter(11, Types.REF_CURSOR);
            statement.registerOutParameter(12, Types.REF_CURSOR);
            statement.registerOutParameter(13, Types.VARCHAR);

            statement.execute();

            if (statement.getResultSet() != null && statement.getResultSet().getArray(11) != null) {
                mcreasoncd = (List<String>) statement.getResultSet().getArray(11);
            }

            if (statement.getResultSet() != null && statement.getResultSet().getArray(12) != null) {
                shorts = (List<String>) statement.getResultSet().getArray(12);
            }

            retText = statement.getString(13);

            logger.info("IMPL==> addReasonInvBulkEnt ==> mcreasoncd res=" + mcreasoncd);
            logger.info("IMPL==> addReasonInvBulkEnt ==> shorts res=" + shorts);
            logger.info("IMPL==> addReasonInvBulkEnt ==> retText res=" + retText);

            response.setMcreasoncd(mcreasoncd);
            response.setShorts(shorts);
            response.setRetText(retText);

            statement.close();

        } catch (SQLException ex) {
            logger.info("IMPL==> addReasonInvBulkEnt ==> exception={}", ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
        logger.info("IMPL==> AddReasonInvBulkEntResponse ==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " addReasonInvBulkEnt Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.of(response);

    }

    @Override
    public void deleteUpload() {

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(
                        messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("delUpload");

        simpleJdbcCall.execute();


    }

}
