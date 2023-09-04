package com.org.entportal.controller;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.common.ResponseUtil;
import com.org.entportal.common.ServiceResponse;
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
import com.org.entportal.service.CollectionService;
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
@RequestMapping("/cmm/collection")
@CrossOrigin(origins = {"http://172.18.62.63:7060"})
public class CMMCollectionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CollectionService collectionService;

    @Autowired
    ResponseUtil<QueryDataResponse> responseUtil;

    @Autowired
    ResponseUtil<UpdCollAmtResponse> updCollAmtResponseUtil;
    
    @Autowired
    ResponseUtil<getShortResponse> updgetShortResponseUtil;

    @Autowired
    ResponseUtil<OldCurrAmtResponse> oldCurrAmtResponseUtil;
    
    @Autowired
    ResponseUtil<SessionflagResponse> sessionflagResponseUtil;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/query")
    public ResponseEntity<ServiceResponse<QueryDataResponse>> query(
            @Valid @RequestBody QueryDataRequest queryDataRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " query Exit");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return responseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<QueryDataResponse> queryDataReponse = collectionService.queryDataEntry(queryDataRequest);

            return responseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    queryDataReponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " qyery Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/updcollamt")
    public ResponseEntity<ServiceResponse<UpdCollAmtResponse>> updCollAmt(
            @Valid @RequestBody UpdCollAmtRequest updCollAmtRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " updCollAmt Exit");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return updCollAmtResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<UpdCollAmtResponse> updCollAmtResponse = collectionService.updCollAmt(updCollAmtRequest);

            return updCollAmtResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    updCollAmtResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " updCollAmt Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }

    }

    @PostMapping("/getShortDetails")
    public ResponseEntity<ServiceResponse<getShortResponse>> getShort(
            @Valid @RequestBody ShortRequest shortRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " getShort Exit");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return updgetShortResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
            }

            Optional<getShortResponse> getshortResponse = collectionService.ShortDetails(shortRequest);
            return updgetShortResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    getshortResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " getShort Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/GetOldCurrAmt")
    public ResponseEntity<ServiceResponse<OldCurrAmtResponse>> getOldCurrAmt(
            @Valid @RequestBody OldCurrAmtRequest oldCurrAmtRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " getOldCurrAmt Exit");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return oldCurrAmtResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<OldCurrAmtResponse> oldCurrAmtResponse = collectionService.oldCurrAmt(oldCurrAmtRequest);

            return oldCurrAmtResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    oldCurrAmtResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " getOldCurrAmt Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }

    }

    @PostMapping("/GetSessionflagdetails")
    public ResponseEntity<ServiceResponse<SessionflagResponse>> GetSessionFlagdetails(
            @Valid @RequestBody SessionflagRequest sessionflagRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " GetSessionflagdetails Exit");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return sessionflagResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
            }

            Optional<SessionflagResponse> sessionflagResponse = collectionService.GetSessionflag(sessionflagRequest);
            return sessionflagResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    sessionflagResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " GetSessionflagdetails Exit");
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
