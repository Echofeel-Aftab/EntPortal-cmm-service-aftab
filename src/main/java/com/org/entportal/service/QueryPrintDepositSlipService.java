package com.org.entportal.service;

import com.org.entportal.request.*;
import com.org.entportal.response.*;

import java.sql.SQLException;
import java.util.Optional;

public interface QueryPrintDepositSlipService {

    public Optional<PrintPaySlipResponse> printPaySlip(PrintPaySlipRequest printPaySlipRequest);

    public Optional<QueryPaySlipResponse> queryPaySlip(QueryPaySlipRequest queryPaySlipRequest);

    public Optional<SaveDataPaySlipResponse> saveDataPaySlip(SaveDataPaySlipRequest saveDataPaySlipRequest) throws SQLException;

    public Optional<FindSerialPaySlipResponse> findSerialPaySlip(FindSerialPaySlipRequest findSerialPaySlipRequest);

    public Optional<SaveActualDataPaySlipResponse> saveActualDataPaySlip(SaveActualDataPaySlipRequest saveActualDataPaySlipRequest) throws SQLException;
}
