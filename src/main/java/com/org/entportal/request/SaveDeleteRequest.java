package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class SaveDeleteRequest {

    private String pcpayslipno;
    private String pcloc;
    private String pcdate;

}
