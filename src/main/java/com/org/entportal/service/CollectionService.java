package com.org.entportal.service;

import com.org.entportal.request.OldCurrAmtRequest;
import com.org.entportal.request.QueryDataRequest;
import com.org.entportal.request.SessionflagRequest;
import com.org.entportal.request.ShortRequest;
import com.org.entportal.request.UpdCollAmtRequest;
import com.org.entportal.response.OldCurrAmtResponse;
import com.org.entportal.response.QueryDataResponse;
import com.org.entportal.response.SessionflagResponse;
import com.org.entportal.response.UpdCollAmtResponse;
import com.org.entportal.response.getShortResponse;

import java.sql.SQLException;
import java.util.Optional;

public interface CollectionService {

    public Optional<QueryDataResponse> queryDataEntry(QueryDataRequest queryDataRequest) throws SQLException;

    public Optional<UpdCollAmtResponse> updCollAmt(UpdCollAmtRequest updCollAmtRequest);
    
    public Optional<getShortResponse> ShortDetails(ShortRequest shortRequest);

    public Optional<OldCurrAmtResponse> oldCurrAmt(OldCurrAmtRequest oldCurrAmtRequest);
    
    public Optional<SessionflagResponse> GetSessionflag(SessionflagRequest sessionflagRequest);

}
