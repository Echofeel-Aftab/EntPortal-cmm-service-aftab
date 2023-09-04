package com.org.entportal.service;

import com.org.entportal.request.BankBranchRequest;
import com.org.entportal.request.BankRequest;
import com.org.entportal.response.BankBranchResponse;
import com.org.entportal.response.BankResponse;

import java.util.Optional;

public interface BankService {

    public Optional<BankResponse> bankAdd(BankRequest bankRequest);

    public Optional<BankBranchResponse> bankBranchMaster(BankBranchRequest bankRequest);
}
