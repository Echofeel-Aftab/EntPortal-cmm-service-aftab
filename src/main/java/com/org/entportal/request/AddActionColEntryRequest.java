package com.org.entportal.request;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AddActionColEntryRequest implements Serializable {

    private String pcserialno;
    private String pcpaytype;
    private String pccolltype;
    private String mccollloccd;
    private String mcdetailsrno;
    private String mccustcode;


}

