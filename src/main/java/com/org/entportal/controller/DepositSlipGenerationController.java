package com.org.entportal.controller;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.common.ResponseUtil;
import com.org.entportal.common.ServiceResponse;
import com.org.entportal.request.AddActionPaySlipRequest;
import com.org.entportal.request.GenerateDepositSlipRequest;
import com.org.entportal.request.SubmitPaySlipRequest;
import com.org.entportal.response.AddActionPaySlipResponse;
import com.org.entportal.response.GenerateDepositSlipResponse;
import com.org.entportal.response.QueryDataResponse;
import com.org.entportal.response.SubmitPaySlipResponse;
import com.org.entportal.service.DepositSlipGenerationService;
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
@RequestMapping("/cmm/depositSlipGeneration")
@CrossOrigin(origins = {"http://172.18.62.63:7060"})
public class DepositSlipGenerationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DepositSlipGenerationService depositSlipGenerationService;

    @Autowired
    ResponseUtil<QueryDataResponse> responseUtil;

    @Autowired
    ResponseUtil<GenerateDepositSlipResponse> generateDepositSlipResponseUtil;
    @Autowired
    ResponseUtil<SubmitPaySlipResponse> submitPaySlipResponseUtil;
    @Autowired
    ResponseUtil<AddActionPaySlipResponse> addActionPaySlipResponseUtil;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/generateDepositSlip")
    public ResponseEntity<ServiceResponse<GenerateDepositSlipResponse>> generateDepositSlip(
            @Valid @RequestBody GenerateDepositSlipRequest generateDepositSlipRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return generateDepositSlipResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<GenerateDepositSlipResponse> response = depositSlipGenerationService.generateDepositSlip(generateDepositSlipRequest);

            return generateDepositSlipResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    response.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/submitPaySlip")
    public ResponseEntity<ServiceResponse<SubmitPaySlipResponse>> submitPaySlip(
            @Valid @RequestBody SubmitPaySlipRequest submitPaySlipRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return submitPaySlipResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<SubmitPaySlipResponse> response = depositSlipGenerationService.submitPaySlip(submitPaySlipRequest);

            return submitPaySlipResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    response.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/addActionPaySlip")
    public ResponseEntity<ServiceResponse<AddActionPaySlipResponse>> addActionPaySlip(
            @Valid @RequestBody AddActionPaySlipRequest addActionPaySlipRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return addActionPaySlipResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<AddActionPaySlipResponse> response = depositSlipGenerationService.addActionPaySlip(addActionPaySlipRequest);

            return addActionPaySlipResponseUtil.buildResponse(HttpStatus.OK,
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
