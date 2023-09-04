package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class AddActionPaySlipRequest {
    private String pcserialno;
    private String pmcpaytype;
    private String mcreasoncd;
    private String mcdetailsrno;
    private String mccustcode;

}
