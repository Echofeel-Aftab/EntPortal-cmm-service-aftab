package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class QueryPaySlipRequest {
    private String pdquerydt1;
    private String pcdepositsrno;
    private String mccollloccd;

}
