package com.org.entportal.service;

import com.org.entportal.request.*;
import com.org.entportal.response.*;

import java.sql.SQLException;
import java.util.Optional;

public interface CollectionEntryService {

    public Optional<DisplayHeaderColEntryResponse> displayHeader(DisplayHeaderColEntryRequest displayHeaderColEntryRequest);

    public Optional<AddActionColEntryResponse> addAction(AddActionColEntryRequest addActionColEntryRequest);

    public Optional<DeleteDataColEntryResponse> deleteData(DeleteDataColEntryRequest deleteDataColEntryRequest);

    public Optional<FindSerialColEntryResponse> findSerial(FindSerialColEntryRequest findSerialColEntryRequest);

    public Optional<SaveDataColEntryResponse> saveData(SaveDataColEntryRequest saveDataColEntryRequest) throws SQLException;

    public Optional<SubmitDetailAwbnoColEntryResponse> submitDetailAwbno(SubmitDetailAwbnoColEntryRequest submitDetailAwbnoColEntryRequest) throws SQLException;

    public Optional<SubmitDetailInvoiceColEntryResponse> submitDetailInvoice(SubmitDetailInvoiceColEntryRequest submitDetailInvoiceColEntryRequest) throws SQLException;

    public Optional<CheckPdaDataResponse> checkPdaData(CheckPdaData data) throws SQLException;

}
