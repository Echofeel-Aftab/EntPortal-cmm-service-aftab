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
public class SaveActualDataPaySlipRequest {
    private String pccollloccd;
    private String pccollempcd;
    private String pccloccode;
    private String pdpayslipdt;
    private String pcpayslipno;
    private String pntotaldepoamt;
    private String pcbankdesc;
    private String pcaccno;
    private String ptotamtcurr;
    private String pmccolltype;
    private String pmcpaytype;
    private List<String> pcawbno;
    private List<String> pcprodcode;
    private List<String> pcorgscrcd;
    private List<String> pncollamt;
    private List<String> pcserialno;
    private String submit;

}
