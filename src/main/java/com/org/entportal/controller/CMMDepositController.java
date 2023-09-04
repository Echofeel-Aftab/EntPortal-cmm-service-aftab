package com.org.entportal.controller;

import com.org.entportal.common.OperationConstants;
import com.org.entportal.common.ResponseUtil;
import com.org.entportal.common.ServiceResponse;
import com.org.entportal.request.ChangeDepositSlipRequest;
import com.org.entportal.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/cmm/deposit")
@CrossOrigin(origins = {"http://172.18.62.63:7060"})
public class CMMDepositController {

    @Autowired
    DepositService depositService;

    @Autowired
    ResponseUtil<Object> responseUtil;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/deposit")
    public ResponseEntity<ServiceResponse<Object>> query(
            @Valid @RequestBody ChangeDepositSlipRequest changeDepositSlipRequest, BindingResult result)
            throws Exception {

        if (result.hasErrors()) {
            String validationErrors = getValidationErrorMessage(result);
            return responseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

        }

        Object changeDepositSlipResponse = depositService.changeDepositSlip(changeDepositSlipRequest);

        return responseUtil.buildResponse(HttpStatus.OK,
                messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                changeDepositSlipResponse);

    }
/*	@PostMapping("/depositslipgeneration")
    public ResponseEntity<ServiceResponse<DepositGenerationSlipResponse>> query(
            @Valid @RequestBody DepositGenerationSlipRequest DepositGenerationSlipRequest, BindingResult result)
            throws Exception {

        if (result.hasErrors()) {
            String validationErrors = getValidationErrorMessage(result);
            return responseUtil.buildValidationResponse(HttpStatus.BAD_REQUEST, validationErrors, false);

        }

        Optional<ChangeDepositSlipResponse> changeDepositSlipResponse = depositService.changeDepositSlip(changeDepositSlipRequest);

        return responseUtil.buildResponse(HttpStatus.OK,
                messageSource.getMessage(OperationConstants.OPERATION_SUCCESSFUL, null, Locale.getDefault()), true,
                changeDepositSlipResponse.get());

    }*/

    private String getValidationErrorMessage(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(bindingResult.getErrorCount()).append(" error(s): ");
        for (ObjectError error : bindingResult.getAllErrors()) {
            System.out.print("VALIIDATION MSG:"
                    + messageSource.getMessage(error.getDefaultMessage(), null, Locale.getDefault()));
            sb.append("[").append(messageSource.getMessage(error.getDefaultMessage(), null, Locale.getDefault()))
                    .append("] ");
        }

        System.out.print("getValidationErrorMessage=" + sb.toString());
        return sb.toString();
    }

}