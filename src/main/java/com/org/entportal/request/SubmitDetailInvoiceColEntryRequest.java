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
public class SubmitDetailInvoiceColEntryRequest {

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
    private List<String> pcbillno;
    private List<String> pcdepositsrno;
    private List<String> pcarea;
    private List<String> pccustcode;
    private List<String> pncollamt;
    private List<String> pdbilldt;
    private List<String> pninvamt;
    private List<String> pnoutstandamt;
    private List<String> pntdsamt;
    private List<String> pnshortamt;
    private List<String> pcreasoncd;
    private List<String> pnshortcoll;
    private List<String> pcremarks;
    private List<String> pcbanknm;
    private List<String> pcbankbranchnm;
    private List<String> pcchequeno;
    private List<String> pdcheqdate;
    private List<String> pcdetailsrno;

}