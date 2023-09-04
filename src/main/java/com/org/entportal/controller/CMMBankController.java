package com.org.entportal.controller;

import com.org.entportal.common.LogParamsEnum;
import com.org.entportal.common.OperationConstants;
import com.org.entportal.common.ResponseUtil;
import com.org.entportal.common.ServiceResponse;
import com.org.entportal.request.BankBranchRequest;
import com.org.entportal.request.BankRequest;
import com.org.entportal.response.BankBranchResponse;
import com.org.entportal.response.BankResponse;
import com.org.entportal.service.BankService;
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
@RequestMapping("/cmm/bank")
@CrossOrigin(origins = {"http://172.18.62.63:7060"})
public class CMMBankController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BankService bankService;

    @Autowired
    ResponseUtil<BankResponse> responseUtil;

    @Autowired
    ResponseUtil<BankBranchResponse> bankBranchResponseUtil;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/add")
    public ResponseEntity<ServiceResponse<BankResponse>> bankAdd(@Valid @RequestBody BankRequest bankRequest,
                                                                 BindingResult result) throws Exception {
        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " bankAdd Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return responseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<BankResponse> bankResponse = bankService.bankAdd(bankRequest);

            return responseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    bankResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " bankAdd Exit");
            logger.info(LogParamsEnum.FINAL_API_DURATION + " " + String.valueOf(exitTime - entryTime));
        }
    }

    @PostMapping("/branch")
    public ResponseEntity<ServiceResponse<BankBranchResponse>> bankBranch(
            @Valid @RequestBody BankBranchRequest bankBranchRequest, BindingResult result)
            throws Exception {

        long entryTime = System.currentTimeMillis();
        logger.info(LogParamsEnum.API_NAME + " bankBranch Entry");
        try {
            if (result.hasErrors()) {
                String validationErrors = getValidationErrorMessage(result);
                return bankBranchResponseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

            }

            Optional<BankBranchResponse> bankBranchResponse = bankService.bankBranchMaster(bankBranchRequest);

            return bankBranchResponseUtil.buildResponse(HttpStatus.OK,
                    messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                    bankBranchResponse.get());
        } finally {
            long exitTime = System.currentTimeMillis();
            logger.info(LogParamsEnum.API_NAME + " bankBranch Exit");
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
