package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class SubmitPaySlipRequest {

    private String pntotamt;
    private String pn2000denom;
    private String pn1000denom;
    private String pn500denom;
    private String pn200denom;
    private String pn100denom;
    private String pn50denom;
    private String pn20denom;
    private String pn10denom;
    private String pn5denom;
    private String pn2denom;
    private String pn1denom;
    private String pnchanges;
    private String pntotal;
    private String pcaccno;
    private String pcbankcd;
    private String pcbankcddsp;
    private String psubmit;
    private String pctype;
    private String pcsubtype;
    private String pcwhich;
    private String pcarea;
    private String pcod;
    private String mccollloccd;
    private String mccollempcd;
}
