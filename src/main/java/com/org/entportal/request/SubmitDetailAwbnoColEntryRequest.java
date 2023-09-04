package com.org.entportal.request;


import lombok.*;

import javax.validation.Valid;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class SubmitDetailAwbnoColEntryRequest {

    private String pctranssrno;
    private String pccolltype;
    private String pcpaytype;
    private String pccollrcptno;
    private String pdcollrcptdt;
    private String pntotalcollamt;
    private String pccollloccd;
    private String pccollempcd;
    private String pccloccode;
    private String psubmit;
    private String paddbut;
    private List<String> pcawbno;
    private List<String> pcprodcode;
    private List<String> pcorgscrcd;
    private List<String> pncollamt;
    private List<String> pcremarks;
    private List<String> pcdepositsrno;
    private List<String> pcbanknm;
    private List<String> pcbankbranchnm;
    private List<String> pcchequeno;
    private List<String> pdcheqdate;

}