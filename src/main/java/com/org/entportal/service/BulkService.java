package com.org.entportal.service;

import com.org.entportal.request.*;
import com.org.entportal.response.*;

import java.sql.SQLException;
import java.util.Optional;

public interface BulkService {

    public void getValFromUpload(GetValFromUploadRequest getValFromUploadRequest);

    public Optional<BulkUploadResponse> bulkUpload(BulkUploadRequest bulkUploadRequest);

    public Optional<UploadStatusResponse> uploadStatus();

    public Optional<EntryScreenResponse> entryScreen(EntryScreenRequest entryScreenRequest);

    public Optional<DoBulkSaveResponse> doBulkSave(DoBulkSaveRequest doBulkSaveRequest) throws SQLException;

    public Optional<EntryScreenInvResponse> entryScreenInv(EntryScreenInvRequest entryScreenInvRequest);

    public Optional<DoBulkSaveInvResponse> doBulkSaveInv(DoBulkSaveInvRequest doBulkSaveInvRequest) throws SQLException;

    public Optional<AddReasonInvBulkEntResponse> addReasonInvBulkEnt(AddReasonInvBulkEntRequest addReasonInvBulkEntRequest) throws SQLException;

    public void deleteUpload();
}
