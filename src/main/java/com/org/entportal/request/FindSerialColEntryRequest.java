package com.org.entportal.request;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FindSerialColEntryRequest implements Serializable {

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

