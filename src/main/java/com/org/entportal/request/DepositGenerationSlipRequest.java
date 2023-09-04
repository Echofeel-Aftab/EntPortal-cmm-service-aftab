package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class DepositGenerationSlipRequest {
    private String codOrOthers;
    private String depositDate;
    private String type;
    private String forWhom;
    private String count;
    private String totalAmount;
    private String rs2000Denomination;
    private String rs1000Denomination;
    private String rs500Denomination;
    private String rs200Denomination;
    private String rs100Denomination;
    private String rs50Denomination;
    private String rs20Denomination;
    private String rs10Denomination;
    private String rs5Denomination;
    private String rs2Denomination;
    private String rs1Denomination;
    private String bankcd;
    private String accno;
    private String total;
    private String bankcddsp;
    private String subtype;
    private String area;
    private String changes;
    private String submit;
}
