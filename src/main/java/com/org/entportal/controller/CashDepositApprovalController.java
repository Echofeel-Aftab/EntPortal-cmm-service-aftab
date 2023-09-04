package com.org.entportal.controller;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.common.ResponseUtil;
import com.org.entportal.common.ServiceResponse;
import com.org.entportal.request.ApproveCollectionRequest;
import com.org.entportal.request.SaveApprovalRequest;
import com.org.entportal.request.SaveDeleteRequest;
import com.org.entportal.response.ApproveCollectionResponse;
import com.org.entportal.response.SaveApprovalResponse;
import com.org.entportal.response.SaveDeleteResponse;
import com.org.entportal.service.CashDepositApprovalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/cmm/cashDepositApproval")
@CrossOrigin(origins = {"http://172.18.62.63:7060"})
public class CashDepositApprovalController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CashDepositApprovalService cashDepositApprovalService;

    @Autowired
    ResponseUtil<SaveApprovalResponse> saveApprovalResponseUtil;
    @Autowired
    ResponseUtil<ApproveCollectionResponse> approveCollectionResponseUtil;
    @Autowired
    ResponseUtil<SaveDeleteResponse> saveDeleteResponseUtil;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/saveApproval")
    public ResponseEntity<ServiceResponse<SaveApprovalResponse>> saveApproval(
            @Valid @RequestBody SaveApprovalRequest saveApprovalRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return saveApprovalResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<SaveApprovalResponse> response = cashDepositApprovalService.saveApproval(saveApprovalRequest);

            return saveApprovalResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    response.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/approveCollection")
    public ResponseEntity<ServiceResponse<ApproveCollectionResponse>> approveCollection(
            @Valid @RequestBody ApproveCollectionRequest approveCollectionRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return approveCollectionResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<ApproveCollectionResponse> response = cashDepositApprovalService.approveCollection(approveCollectionRequest);

            return approveCollectionResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    response.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/saveDelete")
    public ResponseEntity<ServiceResponse<SaveDeleteResponse>> saveDelete(
            @Valid @RequestBody SaveDeleteRequest saveDeleteRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return saveDeleteResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<SaveDeleteResponse> response = cashDepositApprovalService.saveDelete(saveDeleteRequest);

            return saveDeleteResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    response.get());
        } finally {
            long exitTime = System.currentTimeMillis();
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
