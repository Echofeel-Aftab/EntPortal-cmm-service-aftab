package com.org.entportal.service;

import com.org.entportal.request.ApproveCollectionRequest;
import com.org.entportal.request.SaveApprovalRequest;
import com.org.entportal.request.SaveDeleteRequest;
import com.org.entportal.response.ApproveCollectionResponse;
import com.org.entportal.response.SaveApprovalResponse;
import com.org.entportal.response.SaveDeleteResponse;

import java.sql.SQLException;
import java.util.Optional;

public interface CashDepositApprovalService {

    public Optional<SaveApprovalResponse> saveApproval(SaveApprovalRequest saveApprovalRequest) throws SQLException;

    public Optional<ApproveCollectionResponse> approveCollection(ApproveCollectionRequest approveCollectionRequest);

    public Optional<SaveDeleteResponse> saveDelete(SaveDeleteRequest saveDeleteRequest);
}
