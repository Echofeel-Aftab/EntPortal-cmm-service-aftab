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
public class SaveDataColEntryRequest {

    private String pcarea;
    private String pnentry_no;
    private String pctranssrno;
    private String pcdetailsrno;
    private String pdcollrcptdt;
    private String pcdepositsrno;
    private String pcserialno;
    private String pcpaytype;
    private String pccolltype;
    private String pccollrcptno;
    private String pcawbno;
    private String pcprodcode;
    private String pcorgscrcd;
    private String pninvamt;
    private String pnoutstandamt;
    private String pncollamt;
    private String pntdsamt;
    private String pcchequeno;
    private String pdcheqdate;
    private String pcbanknm;
    private String pcbankbranchnm;
    private String pccollloccd;
    private String pccloccode;
    private String pccollempcd;
    private String pcremarks;
    private String ok;
    private String pncrdramt;
    private String pctranstype;
    private List<String> pcreasoncd;
    private List<String> pnshortcoll;


}
