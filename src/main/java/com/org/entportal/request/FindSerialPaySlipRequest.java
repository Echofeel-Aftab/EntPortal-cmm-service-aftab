package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class FindSerialPaySlipRequest {

    private String pcawbno;
    private String pcprodcode;
    private String pcorgscrcd;
    private String pncollamt;
    private String pcbillno;
    private String pdbilldt;
    private String pnoutstandamt;
    private String pcchequeno;
    private String pdcheqdate;
    private String pcbanknm;
    private String pcpaytype;
    private String pccolltype;
}
