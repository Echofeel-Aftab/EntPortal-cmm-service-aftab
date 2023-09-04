package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class BankBranchRequest {

    private String bankCd;
    private String loc;
    private String bnkCd;
    private String updFlg;
    private String updBankcd;
    private String updBankFlg;
    private String submit;

}
