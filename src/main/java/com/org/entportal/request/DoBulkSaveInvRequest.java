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
public class DoBulkSaveInvRequest {

    private List<String> pcbillno;
    private List<String> pdbilldt;
    private List<String> pcentryno;
    private List<String> pninvamt;
    private List<String> pnoutamt;
    private List<String> pncollamt;
    private List<String> pntdsamt;
    private List<String> pccashmemno;
    private List<String> pcchk;
    private List<String> pcrmks;
    private List<String> pctotshrtamt;
    private List<String> pcchk1;
    private String pcarea;
    private String pccustcode;
    private String pinvoice;
    private String pdcolldt;
    private String pccolltype;
    private String pcpaytype;
    private String pcchqno;
    private String pdchqdt;
    private String pcbanknm;
    private String pcbranchnm;
    private String mccollloccd;
    private String mccollempcd;
    private String mccloccode;
    private String mcdcrrole;

}
