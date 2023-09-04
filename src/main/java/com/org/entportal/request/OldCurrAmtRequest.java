package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class OldCurrAmtRequest {
    private String mcolltype;
    private String mpaytype;
    private String mcollempcd;
    private String ptranssrno;
    private String pserialno;
}
