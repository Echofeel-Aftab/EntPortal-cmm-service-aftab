package com.org.entportal.controller;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.common.ResponseUtil;
import com.org.entportal.common.ServiceResponse;
import com.org.entportal.request.*;
import com.org.entportal.response.*;
import com.org.entportal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/cmm/user")
@CrossOrigin(origins = {"http://172.18.62.63:7060"})
public class CMMUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    ResponseUtil<UserAccessResponse> userAccessResponseUtil;

    @Autowired
    ResponseUtil<CreateUserRespone> createUserResonseUtil;

    @Autowired
    ResponseUtil<CreateLoginResponse> createLoginResponseUtil;

    @Autowired
    ResponseUtil<MultiAreaUserLinkResponse> multiAreaUserLinkResponseUtil;

    @Autowired
    ResponseUtil<PreRemittanceActivityResponse> preRemittanceActivityResponseUtil;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/display")
    public ResponseEntity<ServiceResponse<UserAccessResponse>> displayUserAccess(
            @Valid @RequestBody UserAccessRequest userAccessRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " displayUserAccess Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return userAccessResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<UserAccessResponse> userAccessResponse = userService.displayUserAccess(userAccessRequest);

            return userAccessResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    userAccessResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " displayUserAccess Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/createuser")
    public ResponseEntity<ServiceResponse<CreateUserRespone>> createUser(
            @Valid @RequestBody CreateUserRequest createUserRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " createUser Entry");

        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return createUserResonseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<CreateUserRespone> createUserRespone = userService.createUser(createUserRequest);

            return createUserResonseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    createUserRespone.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " createUser Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/createlogin")
    public ResponseEntity<ServiceResponse<CreateLoginResponse>> createLogin(
            @Valid @RequestBody CreateLoginRequest createLoginRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " createLogin Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return createLoginResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<CreateLoginResponse> createLoginResponse = userService.createLogin(createLoginRequest);

            return createLoginResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    createLoginResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " createLogin Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/multiareaaccess")
    public ResponseEntity<ServiceResponse<MultiAreaUserLinkResponse>> multiAreaAccess(
            @Valid @RequestBody MultiAreaUserLinkRequest multiAreaUserLinkRequest, BindingResult result,
            HttpServletRequest request) throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " multiAreaAccess Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return multiAreaUserLinkResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors,
                        false);

            }

            Optional<MultiAreaUserLinkResponse> multiArealUserLinkResponse = userService
                    .multiAreaUserLink(multiAreaUserLinkRequest);

            return multiAreaUserLinkResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    multiArealUserLinkResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " multiAreaAccess Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }

    }

    @PostMapping("/preremittanceactivity")
    public ResponseEntity<ServiceResponse<PreRemittanceActivityResponse>> preRemittanceActivity(
            @Valid @RequestBody PreRemittanceActivityRequest preRemittanceActivityRequest, BindingResult result,
            HttpServletRequest request) throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " preRemittanceActivity Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return preRemittanceActivityResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST,
                        validationErrors, false);

            }

            Optional<PreRemittanceActivityResponse> preRemittanceActivityResponse = userService
                    .preRemittanceActivity(preRemittanceActivityRequest);

            return preRemittanceActivityResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    preRemittanceActivityResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " preRemittanceActivity Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }

    }

    private String getValidationErrorMessage(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(bindingResult.getErrorCount()).append(" error(s): ");
        for (ObjectError error : bindingResult.getAllErrors()) {
            logger.error("VALIIDATION MSG:"
                    + messageSource.getMessage(error.getDefaultMessage(), null, Locale.getDefault()));
            sb.append("[").append(messageSource.getMessage(error.getDefaultMessage(), null, Locale.getDefault()))
                    .append("] ");
        }
        logger.error("getValidationErrorMessage=" + sb.toString());
        return sb.toString();
    }
}
