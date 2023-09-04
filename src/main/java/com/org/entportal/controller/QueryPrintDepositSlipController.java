package com.org.entportal.controller;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.common.ResponseUtil;
import com.org.entportal.common.ServiceResponse;
import com.org.entportal.request.*;
import com.org.entportal.response.*;
import com.org.entportal.service.QueryPrintDepositSlipService;
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
@RequestMapping("/cmm/queryPrintDepositSlip")
@CrossOrigin(origins = {"http://172.18.62.63:7060"})
public class QueryPrintDepositSlipController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    QueryPrintDepositSlipService queryPrintDepositSlipService;

    @Autowired
    ResponseUtil<PrintPaySlipResponse> printPaySlipResponseUtil;
    @Autowired
    ResponseUtil<QueryPaySlipResponse> queryPaySlipResponseUtil;
    @Autowired
    ResponseUtil<SaveDataPaySlipResponse> saveDataPaySlipResponseUtil;
    @Autowired
    ResponseUtil<FindSerialPaySlipResponse> findSerialPaySlipResponseUtil;
    @Autowired
    ResponseUtil<SaveActualDataPaySlipResponse> saveActualDataPaySlipResponseUtil;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/printPaySlip")
    public ResponseEntity<ServiceResponse<PrintPaySlipResponse>> printPaySlip(
            @Valid @RequestBody PrintPaySlipRequest printPaySlipRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return printPaySlipResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<PrintPaySlipResponse> response = queryPrintDepositSlipService.printPaySlip(printPaySlipRequest);

            return printPaySlipResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    response.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/queryPaySlip")
    public ResponseEntity<ServiceResponse<QueryPaySlipResponse>> queryPaySlip(
            @Valid @RequestBody QueryPaySlipRequest queryPaySlipRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return queryPaySlipResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<QueryPaySlipResponse> response = queryPrintDepositSlipService.queryPaySlip(queryPaySlipRequest);

            return queryPaySlipResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    response.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/saveDataPaySlip")
    public ResponseEntity<ServiceResponse<SaveDataPaySlipResponse>> saveDataPaySlip(
            @Valid @RequestBody SaveDataPaySlipRequest saveDataPaySlipRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return saveDataPaySlipResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<SaveDataPaySlipResponse> response = queryPrintDepositSlipService.saveDataPaySlip(saveDataPaySlipRequest);

            return saveDataPaySlipResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    response.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/findSerialPaySlip")
    public ResponseEntity<ServiceResponse<FindSerialPaySlipResponse>> findSerialPaySlip(
            @Valid @RequestBody FindSerialPaySlipRequest findSerialPaySlipRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return findSerialPaySlipResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<FindSerialPaySlipResponse> response = queryPrintDepositSlipService.findSerialPaySlip(findSerialPaySlipRequest);

            return findSerialPaySlipResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    response.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/saveActualDataPaySlip")
    public ResponseEntity<ServiceResponse<SaveActualDataPaySlipResponse>> saveActualDataPaySlip(
            @Valid @RequestBody SaveActualDataPaySlipRequest saveActualDataPaySlipRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return saveActualDataPaySlipResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<SaveActualDataPaySlipResponse> response = queryPrintDepositSlipService.saveActualDataPaySlip(saveActualDataPaySlipRequest);

            return saveActualDataPaySlipResponseUtil.buildResponse(HttpStatus.OK,
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
