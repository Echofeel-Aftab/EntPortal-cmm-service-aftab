package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class PreRemittanceActivityRequest {

    private String date;
    private String awbno;
    private String saveflg;
    private String submit;
    private String process;
    private String data;
    private String date1;
    private String date2;

}
