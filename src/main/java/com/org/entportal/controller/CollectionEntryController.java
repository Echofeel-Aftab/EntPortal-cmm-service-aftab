package com.org.entportal.controller;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.common.ResponseUtil;
import com.org.entportal.common.ServiceResponse;
import com.org.entportal.request.*;
import com.org.entportal.response.*;
import com.org.entportal.service.CollectionEntryService;
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
@RequestMapping("/collect/entry")
@CrossOrigin(origins = {"http://172.18.62.63:7060"})
public class CollectionEntryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MessageSource messageSource;
    @Autowired
    CollectionEntryService collectionEntryService;
    @Autowired
    ResponseUtil<DisplayHeaderColEntryResponse> responseUtilDisplayHeader;
    @Autowired
    ResponseUtil<AddActionColEntryResponse> responseUtilAddAction;
    @Autowired
    ResponseUtil<DeleteDataColEntryResponse> responseUtilDeleteData;
    @Autowired
    ResponseUtil<SaveDataColEntryResponse> responseUtilSaveData;
    @Autowired
    ResponseUtil<FindSerialColEntryResponse> responseUtilFindSerial;
    @Autowired
    ResponseUtil<SubmitDetailInvoiceColEntryResponse> responseUtilSubmitDetailInvoice;
    @Autowired
    ResponseUtil<SubmitDetailAwbnoColEntryResponse> responseUtilSubmitDetailAwbno;

    @Autowired
    ResponseUtil<CheckPdaDataResponse> checkPdaResponseResponseUtil;

    @PostMapping(path = "displayHeader")
    public ResponseEntity<ServiceResponse<DisplayHeaderColEntryResponse>> getGenerateBill(@Valid @RequestBody DisplayHeaderColEntryRequest displayHeaderColEntryRequest,
                                                                                          BindingResult result) throws Exception {

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " displayHeader");

        if (result.hasErrors()) {
            String validationErrors = getValidationErrorMessage(result);
            logger.info(validationErrors);
            return responseUtilDisplayHeader.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
        }
        try {
            DisplayHeaderColEntryResponse dpdResponse = collectionEntryService.displayHeader(displayHeaderColEntryRequest).get();

            return responseUtilDisplayHeader.buildResponse(HttpStatus.OK, messageSource.getMessage(
                    OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true, dpdResponse);
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " displayHeader Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }

    }

    @PostMapping(path = "addAction")
    public ResponseEntity<ServiceResponse<AddActionColEntryResponse>> getGenerateBill(@Valid @RequestBody AddActionColEntryRequest addActionColEntryRequest,
                                                                                      BindingResult result,
                                                                                      HttpServletRequest request) throws Exception {

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " addAction");

        if (result.hasErrors()) {
            String validationErrors = getValidationErrorMessage(result);
            logger.info(validationErrors);
            return responseUtilAddAction.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
        }
        try {
            AddActionColEntryResponse dpdResponse = collectionEntryService.addAction(addActionColEntryRequest).get();

            return responseUtilAddAction.buildResponse(HttpStatus.OK, messageSource.getMessage(
                    OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true, dpdResponse);
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " addAction Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }

    }

    @PostMapping(path = "findSerial")
    public ResponseEntity<ServiceResponse<FindSerialColEntryResponse>> getGenerateBill(@Valid @RequestBody FindSerialColEntryRequest findSerialColEntryRequest,
                                                                                       BindingResult result,
                                                                                       HttpServletRequest request) throws Exception {

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " findSerial");

        if (result.hasErrors()) {
            String validationErrors = getValidationErrorMessage(result);
            logger.info(validationErrors);
            return responseUtilFindSerial.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
        }
        try {
            FindSerialColEntryResponse dpdResponse = collectionEntryService.findSerial(findSerialColEntryRequest).get();

            return responseUtilFindSerial.buildResponse(HttpStatus.OK, messageSource.getMessage(
                    OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true, dpdResponse);
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " findSerial Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }

    }

    @PostMapping(path = "deleteData")
    public ResponseEntity<ServiceResponse<DeleteDataColEntryResponse>> getGenerateBill(@Valid @RequestBody DeleteDataColEntryRequest deleteDataColEntryRequest,
                                                                                       BindingResult result,
                                                                                       HttpServletRequest request) throws Exception {

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " deleteData");

        if (result.hasErrors()) {
            String validationErrors = getValidationErrorMessage(result);
            logger.info(validationErrors);
            return responseUtilDeleteData.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
        }
        try {
            DeleteDataColEntryResponse dpdResponse = collectionEntryService.deleteData(deleteDataColEntryRequest).get();

            return responseUtilDeleteData.buildResponse(HttpStatus.OK, messageSource.getMessage(
                    OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true, dpdResponse);
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " deleteData Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }

    }

    @PostMapping(path = "saveData")
    public ResponseEntity<ServiceResponse<SaveDataColEntryResponse>> getGenerateBill(@Valid @RequestBody SaveDataColEntryRequest saveDataColEntryRequest,
                                                                                     BindingResult result,
                                                                                     HttpServletRequest request) throws Exception {

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " saveData");

        if (result.hasErrors()) {
            String validationErrors = getValidationErrorMessage(result);
            logger.info(validationErrors);
            return responseUtilSaveData.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
        }
        try {
            SaveDataColEntryResponse dpdResponse = collectionEntryService.saveData(saveDataColEntryRequest).get();

            return responseUtilSaveData.buildResponse(HttpStatus.OK, messageSource.getMessage(
                    OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true, dpdResponse);
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " saveData Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }

    }

    @PostMapping(path = "submitDetailInvoice")
    public ResponseEntity<ServiceResponse<SubmitDetailInvoiceColEntryResponse>> getGenerateBill(@Valid @RequestBody SubmitDetailInvoiceColEntryRequest submitDetailInvoiceColEntryRequest,
                                                                                                BindingResult result,
                                                                                                HttpServletRequest request) throws Exception {

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " submitDetailInvoice");

        if (result.hasErrors()) {
            String validationErrors = getValidationErrorMessage(result);
            logger.info(validationErrors);
            return responseUtilSubmitDetailInvoice.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
        }
        try {
            SubmitDetailInvoiceColEntryResponse dpdResponse = collectionEntryService.submitDetailInvoice(submitDetailInvoiceColEntryRequest).get();

            return responseUtilSubmitDetailInvoice.buildResponse(HttpStatus.OK, messageSource.getMessage(
                    OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true, dpdResponse);
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " submitDetailInvoice Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }

    }

    @PostMapping(path = "submitDetailAwbno")
    public ResponseEntity<ServiceResponse<SubmitDetailAwbnoColEntryResponse>> getGenerateBill(@Valid @RequestBody SubmitDetailAwbnoColEntryRequest submitDetailAwbnoColEntryRequest,
                                                                                              BindingResult result,
                                                                                              HttpServletRequest request) throws Exception {

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " submitDetailAwbno");

        if (result.hasErrors()) {
            String validationErrors = getValidationErrorMessage(result);
            logger.info(validationErrors);
            return responseUtilSubmitDetailAwbno.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
        }
        try {
            SubmitDetailAwbnoColEntryResponse dpdResponse = collectionEntryService.submitDetailAwbno(submitDetailAwbnoColEntryRequest).get();

            return responseUtilSubmitDetailAwbno.buildResponse(HttpStatus.OK, messageSource.getMessage(
                    OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true, dpdResponse);
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " submitDetailAwbno Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }

    }

    @PostMapping(path = "checkPdaData")
    public ResponseEntity<ServiceResponse<CheckPdaDataResponse>> checkPdaData(@Valid @RequestBody CheckPdaData data,
                                                                              BindingResult result,
                                                                              HttpServletRequest request) throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " checkPdaData Entry");

        if (result.hasErrors()) {
            String validationErrors = getValidationErrorMessage(result);
            logger.info(validationErrors);
            return checkPdaResponseResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
        }
        try {
            Optional<CheckPdaDataResponse> checkPdaResponse = collectionEntryService.checkPdaData(data);

            return checkPdaResponseResponseUtil.buildResponse(HttpStatus.OK, messageSource.getMessage(
                    OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true, checkPdaResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " checkPdaData Exit");
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
