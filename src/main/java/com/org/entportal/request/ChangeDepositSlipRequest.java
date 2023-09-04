package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class ChangeDepositSlipRequest {
    private String pcpayslipno;
    private String submit;
    private String ccollLoccd;
    private String ccollEmpcd;
    private String cclocCode;
    private String depositsrno;
    private String depositsrno1;
    private String cdcrRole;
    private String payslipno;
    private String payslipdt;
    private String totaldepoamt;
    private String bankdesc;
    private String accno;
    private String totamtcurr;
    private String colltype;
}
