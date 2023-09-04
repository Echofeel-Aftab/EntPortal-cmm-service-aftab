package com.org.entportal.service;

import com.org.entportal.request.*;
import com.org.entportal.response.*;

import java.sql.SQLException;
import java.util.Optional;

public interface UserService {

    public Optional<UserAccessResponse> displayUserAccess(UserAccessRequest userAccessRequest);

    public Optional<CreateUserRespone> createUser(CreateUserRequest createUserRequest) throws SQLException;

    public Optional<CreateLoginResponse> createLogin(CreateLoginRequest createLoginRequest);

    public Optional<MultiAreaUserLinkResponse> multiAreaUserLink(MultiAreaUserLinkRequest multiAreaUserLinkRequest);

    public Optional<PreRemittanceActivityResponse> preRemittanceActivity(
            PreRemittanceActivityRequest preRemittanceActivityRequest);

}
