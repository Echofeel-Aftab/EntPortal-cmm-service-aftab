package com.org.entportal.service.impl;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.request.*;
import com.org.entportal.response.*;
import com.org.entportal.service.CollectionEntryService;
import com.org.entportal.spcall.FunctionCall;
import com.org.entportal.spcall.StoredProcedureCall;
import lombok.extern.slf4j.Slf4j;
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
import java.util.*;

@Slf4j
@Service
public class CollectionEntryServiceImpl implements CollectionEntryService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    private StoredProcedureCall spcall;

    @Autowired
    private FunctionCall fnCall;

    private static java.sql.Array GetSqlArray(String typeName, java.util.List<String> list, Connection connection) throws SQLException {
        ArrayDescriptor aray_descriptior = ArrayDescriptor.createDescriptor(typeName, connection);
        java.sql.Array sql_array = new oracle.sql.ARRAY(aray_descriptior, connection, list.toArray(new String[list.size()]));
        return sql_array;
    }

    @PostConstruct
    void init() {
        // o_name and O_NAME, same
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public Optional<DisplayHeaderColEntryResponse> displayHeader(DisplayHeaderColEntryRequest displayHeaderColEntryRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " getDisplayHeaderResponse");
        long entryTime = System.currentTimeMillis();


        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("pctranssrno", displayHeaderColEntryRequest.getPctranssrno() == null ? null : displayHeaderColEntryRequest.getPctranssrno())
                .addValue("pcserialno", displayHeaderColEntryRequest.getPcserialno() == null ? null : displayHeaderColEntryRequest.getPcserialno())
                .addValue("mccollempcd", displayHeaderColEntryRequest.getMccollempcd() == null ? null : displayHeaderColEntryRequest.getMccollempcd())
                .addValue("mccollloccd", displayHeaderColEntryRequest.getMccollloccd() == null ? null : displayHeaderColEntryRequest.getMccollloccd())
                .addValue("mcdcrrole", displayHeaderColEntryRequest.getMcdcrrole() == null ? null : displayHeaderColEntryRequest.getMcdcrrole());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("get_display_header");

        Map displayHeaderSQLMap = null;
        Object objectMap = spcall.callSProc(simpleJdbcCall, sqlParameterSource, "get_display_header");
        if (objectMap != null) {
            displayHeaderSQLMap = (Map) objectMap;
        }

        DisplayHeaderColEntryResponse response = new DisplayHeaderColEntryResponse();

        if (displayHeaderSQLMap != null && displayHeaderSQLMap.size() > 0) {
            Object collheader_object = displayHeaderSQLMap.get("collheader");
            if (collheader_object != null) {
                response.setCollheader((List) collheader_object);
            }
            Object colldetail_object = displayHeaderSQLMap.get("colldetail");
            if (colldetail_object != null) {
                response.setColldetail((List) colldetail_object);
            }
            Object pccolltype_object = displayHeaderSQLMap.get("dropdown_pccolltype");
            if (pccolltype_object != null) {
                response.setPccolltype((List) pccolltype_object);
            }
            Object dropdown_pcpaytype = displayHeaderSQLMap.get("dropdown_pcpaytype");
            if (dropdown_pcpaytype != null) {
                response.setPcpaytype((List) dropdown_pcpaytype);
            }
        }

        if (response != null) {
            logger.debug("PDA==> getDisplayHeaderResponse==>response.get(0)=" + response);
            return Optional.of(response);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.empty();
    }

    @Override
    public Optional<AddActionColEntryResponse> addAction(AddActionColEntryRequest addActionColEntryRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " getAddActionResponse");
        long entryTime = System.currentTimeMillis();


        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("pcserialno", addActionColEntryRequest.getPcserialno() == null ? null : addActionColEntryRequest.getPcserialno())
                .addValue("pcpaytype", addActionColEntryRequest.getPcpaytype() == null ? null : addActionColEntryRequest.getPcpaytype())
                .addValue("pccolltype", addActionColEntryRequest.getPccolltype() == null ? null : addActionColEntryRequest.getPccolltype())
                .addValue("mccollloccd", addActionColEntryRequest.getMccollloccd() == null ? null : addActionColEntryRequest.getMccollloccd())
                .addValue("mcdetailsrno", addActionColEntryRequest.getMcdetailsrno() == null ? null : addActionColEntryRequest.getMcdetailsrno())
                .addValue("mccustcode", addActionColEntryRequest.getMccustcode() == null ? null : addActionColEntryRequest.getMccustcode());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("ADD_ACTION");

        Map addActionSQLMap = null;
        Object objectMap = spcall.callSProc(simpleJdbcCall, sqlParameterSource, "ADD_ACTION");
        if (objectMap != null) {
            addActionSQLMap = (Map) objectMap;
        }

        AddActionColEntryResponse response = new AddActionColEntryResponse();

        if (addActionSQLMap != null && addActionSQLMap.size() > 0) {
            String mccustname = (String) addActionSQLMap.get("mccustname");
            response.setMccustname(mccustname);
            String rettext = (String) addActionSQLMap.get("rettext");
            response.setRettext(rettext);
            Object detail_object = addActionSQLMap.get("detail");
            if (detail_object != null) {
                response.setDetail((List) detail_object);
            }
            Object dropdown_pcreasoncd_object = addActionSQLMap.get("dropdown_pcreasoncd");
            if (dropdown_pcreasoncd_object != null) {
                response.setDropdown_pcreasoncd((List) dropdown_pcreasoncd_object);
            }

        }

        if (response != null) {
            logger.debug("PDA==> getAddActionResponse==>response.get(0)=" + response);
            return Optional.of(response);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.empty();
    }

    @Override
    public Optional<DeleteDataColEntryResponse> deleteData(DeleteDataColEntryRequest deleteDataColEntryRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " getDeleteDataResponse");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pcserialno", deleteDataColEntryRequest.getPcserialno());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withFunctionName("delete_data");

        Object result = fnCall.callFunctionForObject(simpleJdbcCall, params);
        DeleteDataColEntryResponse response = new DeleteDataColEntryResponse();
        if (result != null) {
            Optional o = (Optional) result;
            response.setVreturn(o.get().toString());
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));

        if (response != null)
            return Optional.of(response);

        return Optional.empty();
    }

    @Override
    public Optional<FindSerialColEntryResponse> findSerial(FindSerialColEntryRequest findSerialColEntryRequest) {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " getFindSerialResponse");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("pncollamt", findSerialColEntryRequest.getPncollamt() == null ? null : findSerialColEntryRequest.getPncollamt())
                .addValue("pcbillno", findSerialColEntryRequest.getPcbillno() == null ? null : findSerialColEntryRequest.getPcbillno())
                .addValue("pdbilldt", findSerialColEntryRequest.getPdbilldt() == null ? null : findSerialColEntryRequest.getPdbilldt())
                .addValue("pnoutstandamt", findSerialColEntryRequest.getPnoutstandamt() == null ? null : findSerialColEntryRequest.getPnoutstandamt())
                .addValue("pcchequeno", findSerialColEntryRequest.getPcchequeno() == null ? null : findSerialColEntryRequest.getPcchequeno())
                .addValue("pdcheqdate", findSerialColEntryRequest.getPdcheqdate() == null ? null : findSerialColEntryRequest.getPdcheqdate())
                .addValue("pcbanknm", findSerialColEntryRequest.getPcbanknm() == null ? null : findSerialColEntryRequest.getPcbanknm())
                .addValue("pcpaytype", findSerialColEntryRequest.getPcpaytype() == null ? null : findSerialColEntryRequest.getPcpaytype())
                .addValue("pccolltype", findSerialColEntryRequest.getPccolltype() == null ? null : findSerialColEntryRequest.getPccolltype());

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(messageSource.getMessage(OperationConstants.CMM_CATALOG_NAME, null, Locale.getDefault()))
                .withProcedureName("find_serial");

        Map findSerialSQLMap = null;
        Object objectMap = spcall.callSProc(simpleJdbcCall, sqlParameterSource, "find_serial");
        if (objectMap != null) {
            findSerialSQLMap = (Map) objectMap;
        }

        FindSerialColEntryResponse response = new FindSerialColEntryResponse();

        if (findSerialSQLMap != null && findSerialSQLMap.size() > 0) {
            String mcserialno = (String) findSerialSQLMap.get("mcserialno");
            response.setMcserialno(mcserialno);
        }

        if (response != null) {
            logger.debug("PDA==> getFindSerialResponse==>response.get(0)=" + response);
            return Optional.of(response);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.empty();
    }

    @Override
    public Optional<SaveDataColEntryResponse> saveData(SaveDataColEntryRequest request) throws SQLException {

        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " saveDataColEntry Entry");
        long entryTime = System.currentTimeMillis();

        SaveDataColEntryResponse response = new SaveDataColEntryResponse();

        Connection oracleConnection = null;

        String retText = null;

        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            oracleConnection = connection.isWrapperFor(OracleConnection.class) ? connection.unwrap(OracleConnection.class) : connection;

            CallableStatement statement = oracleConnection.prepareCall("{call WEBDCR2.save_data(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

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
            statement.registerOutParameter(32, Types.VARCHAR);
            statement.registerOutParameter(33, Types.VARCHAR);

            statement.execute();

            retText = statement.getString(31);
            String mcserialno = statement.getString(32);
            String mctranssrno = statement.getString(33);

            response.setRettext(retText);
            response.setMcserialno(mcserialno);
            response.setMctranssrno(mctranssrno);
            statement.close();
            logger.info("connection closed {}", connection.isClosed());
            logger.info("internal connection closed {}", oracleConnection.isClosed());

        } catch (SQLException ex) {
            logger.info("IMPL==> SaveData ==> exception={}", ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

        logger.info("IMPL==> SaveDataColEntryRespone==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " saveDataColEntry Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));

        return Optional.of(response);
    }

    @Override
    public Optional<SubmitDetailAwbnoColEntryResponse> submitDetailAwbno(SubmitDetailAwbnoColEntryRequest request) throws SQLException {
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " submitDetailAwbnoColEntry Entry");
        long entryTime = System.currentTimeMillis();

        SubmitDetailAwbnoColEntryResponse response = new SubmitDetailAwbnoColEntryResponse();

        Connection oracleConnection = null;

        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            oracleConnection = connection.isWrapperFor(OracleConnection.class) ? connection.unwrap(OracleConnection.class) : connection;

            System.out.println(" request.getPntotalcollamt() " + request.getPntotalcollamt());
            System.out.println(" request.getPncollamt() " + request.getPncollamt());

            CallableStatement statement = oracleConnection.prepareCall("{call WEBDCR2.submit_detail_awbno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString(1, request.getPctranssrno());
            statement.setString(2, request.getPccolltype());
            statement.setString(3, request.getPcpaytype());
            statement.setString(4, request.getPccollrcptno());
            statement.setString(5, request.getPdcollrcptdt());
            statement.setString(6, request.getPntotalcollamt());
            statement.setString(7, request.getPccollloccd());
            statement.setString(8, request.getPccollempcd());
            statement.setString(9, request.getPccloccode());
            statement.setString(10, request.getPsubmit());
            statement.setString(11, request.getPaddbut());
            java.sql.Array pcawbno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcawbno(), oracleConnection);
            statement.setArray(12, pcawbno_sql_array);
            java.sql.Array pcprodcode_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcprodcode(), oracleConnection);
            statement.setArray(13, pcprodcode_sql_array);
            java.sql.Array pcorgscrcd_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcorgscrcd(), oracleConnection);
            statement.setArray(14, pcorgscrcd_sql_array);
            java.sql.Array pncollamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPncollamt(), oracleConnection);
            statement.setArray(15, pncollamt_sql_array);
            java.sql.Array pcremarks_sql_array = GetSqlArray("T_TABLE_VARCHAR2_400", request.getPcremarks(), oracleConnection);
            statement.setArray(16, pcremarks_sql_array);
            java.sql.Array pcdepositsrno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcdepositsrno(), oracleConnection);
            statement.setArray(17, pcdepositsrno_sql_array);
            java.sql.Array pcbanknm_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcbanknm(), oracleConnection);
            statement.setArray(18, pcbanknm_sql_array);
            java.sql.Array pcbankbranchnm_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcbankbranchnm(), oracleConnection);
            statement.setArray(19, pcbankbranchnm_sql_array);
            java.sql.Array pcchequeno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcchequeno(), oracleConnection);
            statement.setArray(20, pcchequeno_sql_array);
            java.sql.Array pdcheqdate_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPdcheqdate(), oracleConnection);
            statement.setArray(21, pdcheqdate_sql_array);

            statement.execute();

            statement.close();
        } catch (SQLException ex) {
            logger.info("IMPL==> SubmitDetailAwbno ==> exception={}", ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

        logger.info("IMPL==> SubmitDetailAwbnoColEntryRespone==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " submitDetailAwbnoColEntry Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));

        return Optional.of(response);
    }

    @Override
    public Optional<SubmitDetailInvoiceColEntryResponse> submitDetailInvoice(SubmitDetailInvoiceColEntryRequest request) throws SQLException {
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " submitDetailInvoiceColEntry Entry");
        long entryTime = System.currentTimeMillis();

        SubmitDetailInvoiceColEntryResponse response = new SubmitDetailInvoiceColEntryResponse();

        Connection oracleConnection = null;

        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            oracleConnection = connection.isWrapperFor(OracleConnection.class) ? connection.unwrap(OracleConnection.class) : connection;

            CallableStatement statement = oracleConnection.prepareCall("{call WEBDCR2.submit_detail_invoice(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            System.out.println(" request.getPntotalcollamt() " + request.getPntotalcollamt());
            System.out.println(" request.getPncollamt() " + request.getPncollamt());

            statement.setString(1, request.getPctranssrno());
            statement.setString(2, request.getPccolltype());
            statement.setString(3, request.getPcpaytype());
            statement.setString(4, request.getPccollrcptno());
            statement.setString(5, request.getPdcollrcptdt());
            statement.setString(6, request.getPntotalcollamt());
            statement.setString(7, request.getPccollloccd());
            statement.setString(8, request.getPccollempcd());
            statement.setString(9, request.getPccloccode());
            statement.setString(10, request.getPsubmit());
            statement.setString(11, request.getPaddbut());
            java.sql.Array pcbillno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcbillno(), oracleConnection);
            statement.setArray(12, pcbillno_sql_array);
            java.sql.Array pcdepositsrno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcdepositsrno(), oracleConnection);
            statement.setArray(13, pcdepositsrno_sql_array);
            java.sql.Array pcarea_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcarea(), oracleConnection);
            statement.setArray(14, pcarea_sql_array);
            java.sql.Array pccustcode_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPccustcode(), oracleConnection);
            statement.setArray(15, pccustcode_sql_array);
            java.sql.Array pncollamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPncollamt(), oracleConnection);
            statement.setArray(16, pncollamt_sql_array);
            java.sql.Array pdbilldt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPdbilldt(), oracleConnection);
            statement.setArray(17, pdbilldt_sql_array);
            java.sql.Array pninvamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPninvamt(), oracleConnection);
            statement.setArray(18, pninvamt_sql_array);
            java.sql.Array pnoutstandamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPnoutstandamt(), oracleConnection);
            statement.setArray(19, pnoutstandamt_sql_array);
            java.sql.Array pntdsamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPntdsamt(), oracleConnection);
            statement.setArray(20, pntdsamt_sql_array);
            java.sql.Array pnshortamt_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPnshortamt(), oracleConnection);
            statement.setArray(21, pnshortamt_sql_array);
            java.sql.Array pcreasoncd_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcreasoncd(), oracleConnection);
            statement.setArray(22, pcreasoncd_sql_array);
            java.sql.Array pnshortcoll_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPnshortcoll(), oracleConnection);
            statement.setArray(23, pnshortcoll_sql_array);
            java.sql.Array pcremarks_sql_array = GetSqlArray("T_TABLE_VARCHAR2_400", request.getPcremarks(), oracleConnection);
            statement.setArray(24, pcremarks_sql_array);
            java.sql.Array pcbanknm_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcbanknm(), oracleConnection);
            statement.setArray(25, pcbanknm_sql_array);
            java.sql.Array pcbankbranchnm_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcbankbranchnm(), oracleConnection);
            statement.setArray(26, pcbankbranchnm_sql_array);
            java.sql.Array pcchequeno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcchequeno(), oracleConnection);
            statement.setArray(27, pcchequeno_sql_array);
            java.sql.Array pdcheqdate_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPdcheqdate(), oracleConnection);
            statement.setArray(28, pdcheqdate_sql_array);
            java.sql.Array pcdetailsrno_sql_array = GetSqlArray("T_TABLE_VARCHAR2_50", request.getPcdetailsrno(), oracleConnection);
            statement.setArray(29, pcdetailsrno_sql_array);

            statement.execute();

            statement.close();
        } catch (SQLException ex) {
            logger.info("IMPL==> SubmitDetailInvoice ==> exception={}", ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

        logger.info("IMPL==> SubmitDetailInvoiceColEntryRespone==>response=" + response);

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " submitDetailInvoiceColEntry Exit");
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));

        return Optional.of(response);
    }

    @Override
    public Optional<CheckPdaDataResponse> checkPdaData(CheckPdaData data) throws SQLException {
        logger.info(LogParamsEnum.SERVICE_METHOD_NAME + " checkPdaData");
        long entryTime = System.currentTimeMillis();

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("pcfield", data.getPcfield() == null ? null : data.getPcfield())
                .addValue("pdata", data.getPdata() == null ? null : data.getPdata())
                .addValue("coption", data.getCoption() == null ? null : data.getCoption());


        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("WEBDCR2")
                .withProcedureName("checkpdadata");

        Map checkMap = null;
        Map objectMap = spcall.callSProc(simpleJdbcCall, sqlParameterSource, "checkpdadata");
        if (objectMap != null) {
            checkMap = (Map) objectMap;
        }

        CheckPdaDataResponse response = new CheckPdaDataResponse();
        List<CheckPda> checkPdaList = new ArrayList<>();
        if (checkMap != null && checkMap.size() > 0) {
            checkPdaList = (ArrayList<CheckPda>) objectMap.get("retVal");
        }

        response = CheckPdaDataResponse.builder().checkPdaData(checkPdaList).build();
        if (response != null) {
            logger.debug("PDA==> checkPdaData==>response.get(0)=" + response);
            return Optional.of(response);
        }

        long exitTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        return Optional.empty();
    }
}
