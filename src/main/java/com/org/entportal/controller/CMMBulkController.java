package com.org.entportal.controller;

import com.opencsv.CSVReader;
import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.common.ResponseUtil;
import com.org.entportal.common.ServiceResponse;
import com.org.entportal.request.*;
import com.org.entportal.response.*;
import com.org.entportal.service.BulkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/cmm/bulk")
@CrossOrigin(origins = {"http://172.18.62.63:7060"})
public class CMMBulkController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BulkService bulkService;

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    ResponseUtil<BulkUploadResponse> bulkUploadResponseUtil;

    @Autowired
    ResponseUtil<UploadStatusResponse> uploadStatusResponseUtil;

    @Autowired
    ResponseUtil<EntryScreenResponse> entryScreenResponseUtil;

    @Autowired
    ResponseUtil<DoBulkSaveResponse> doBulkSaveResponseUtil;

    @Autowired
    ResponseUtil<EntryScreenInvResponse> entryScreenInvResponseUtil;

    @Autowired
    ResponseUtil<DoBulkSaveInvResponse> doBulkSaveInvResponseUtil;

    @Autowired
    ResponseUtil<AddReasonInvBulkEntResponse> addReasonInvBulkEntResponseUtil;

    @Autowired
    MessageSource messageSource;

    private List<GetValFromUploadRequest> getCommentsUploadRequestsFromFile(MultipartFile file) throws Exception {


        InputStream inputStream = file.getInputStream();
        List<GetValFromUploadRequest> getValFromUploadRequestList = new ArrayList<>();
        String[] nextLine;
        CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
        csvReader.skip(1);
        while ((nextLine = csvReader.readNext()) != null) {
            GetValFromUploadRequest getValFromUploadRequest = new GetValFromUploadRequest();

            getValFromUploadRequest.setAwbno(nextLine[0]);
            getValFromUploadRequest.setCollamt(nextLine[1]);
            getValFromUploadRequest.setRemarks(nextLine[2]);
            getValFromUploadRequest.setChequeno(nextLine[3]);
            getValFromUploadRequest.setCheqdate(nextLine[4]);

            getValFromUploadRequestList.add(getValFromUploadRequest);

        }

        return getValFromUploadRequestList;
    }

    @PostMapping("/getvalfromupload")
    public ResponseEntity<ServiceResponse<UploadStatusResponse>> getValFromUpload(@RequestParam("cfile") MultipartFile file, @RequestParam("empCode") String empCode,
                                                                                  @RequestParam("scrCd") String scrCd, @RequestParam("collrcptdt") String collRcptDt,
                                                                                  @RequestParam("colltype") String collType, @RequestParam("paytype") String payType) throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " getValFromUpload Entry");
        bulkService.deleteUpload();
        try {
            List<GetValFromUploadRequest> uploadRequestList1 = getCommentsUploadRequestsFromFile(file);
            for (GetValFromUploadRequest req : uploadRequestList1) {
                req.setEmplcode(empCode);
                req.setScrcd(scrCd);
                req.setCollrcptdt(collRcptDt);
                req.setColltype(collType);
                req.setPaytype(payType);
                bulkService.getValFromUpload(req);
            }

            Optional<UploadStatusResponse> uploadStatusResponse = bulkService.uploadStatus();
            return responseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    uploadStatusResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " getValFromUpload Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/bulkupload")
    public ResponseEntity<ServiceResponse<BulkUploadResponse>> bulkUpload(
            @Valid @RequestBody BulkUploadRequest bulkUploadRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " bulkUpload Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return responseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<BulkUploadResponse> bulkUploadResponse = bulkService.bulkUpload(bulkUploadRequest);

            return responseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    bulkUploadResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " bulkUpload Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/uploadstatus")
    public ResponseEntity<ServiceResponse<UploadStatusResponse>> uploadStatus(
            @Valid @RequestBody UploadStatusRequest uploadStatusRequest, BindingResult result,
            HttpServletRequest request) throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " uploadStatus Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return uploadStatusResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors,
                        false);
            }
            Optional<UploadStatusResponse> uploadStatusResponse = bulkService.uploadStatus();
            return uploadStatusResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    uploadStatusResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " uploadStatus Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/entryscreen")
    public ResponseEntity<ServiceResponse<EntryScreenResponse>> entryScreen(
            @Valid @RequestBody EntryScreenRequest entryScreenRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " entryScreen Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return entryScreenResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<EntryScreenResponse> entryScreenResponse = bulkService.entryScreen(entryScreenRequest);

            return entryScreenResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    entryScreenResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " entryScreen Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/dobulksave")
    public ResponseEntity<ServiceResponse<DoBulkSaveResponse>> doBulkSave(
            @Valid @RequestBody DoBulkSaveRequest doBulkSaveRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " doBulkSave Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return doBulkSaveResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<DoBulkSaveResponse> doBulkSaveResponse = bulkService.doBulkSave(doBulkSaveRequest);

            return doBulkSaveResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    doBulkSaveResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " doBulkSave Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/entryscreeninv")
    public ResponseEntity<ServiceResponse<EntryScreenInvResponse>> entryScreenInv(
            @Valid @RequestBody EntryScreenInvRequest entryScreenInvRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " entryScreenInv Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return entryScreenInvResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<EntryScreenInvResponse> entryScreenInvResponse = bulkService.entryScreenInv(entryScreenInvRequest);

            return entryScreenInvResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    entryScreenInvResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " entryScreenInv Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/dobulksaveinv")
    public ResponseEntity<ServiceResponse<DoBulkSaveInvResponse>> doBulkSave(
            @Valid @RequestBody DoBulkSaveInvRequest doBulkSaveInvRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " doBulkSave Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return doBulkSaveInvResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<DoBulkSaveInvResponse> doBulkSaveInvResponse = bulkService.doBulkSaveInv(doBulkSaveInvRequest);

            return doBulkSaveInvResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    doBulkSaveInvResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " doBulkSave Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/addreasoninvbulkent")
    public ResponseEntity<ServiceResponse<AddReasonInvBulkEntResponse>> addReasonInvBulkEnt(
            @Valid @RequestBody AddReasonInvBulkEntRequest addReasonInvBulkEntRequest, BindingResult result)
            throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " addReasonInvBulkEnt Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return addReasonInvBulkEntResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);
            }

            Optional<AddReasonInvBulkEntResponse> addReasonInvBulkEntResponse = bulkService.addReasonInvBulkEnt(addReasonInvBulkEntRequest);

            return addReasonInvBulkEntResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    addReasonInvBulkEntResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " addReasonInvBulkEnt Exit");
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
