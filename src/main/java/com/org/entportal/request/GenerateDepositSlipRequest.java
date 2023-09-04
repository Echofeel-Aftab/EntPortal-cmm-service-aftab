package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class GenerateDepositSlipRequest {

    private String pctype;
    private String pcsubtype;
    private String pcwhich;
    private String pcod;
    private String mccollloccd;
    private String mccollempcd;

}
