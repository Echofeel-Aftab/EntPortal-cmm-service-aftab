package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class UpdCollAmtRequest {

    private String awbNo;

    private float colAmt;

    private float oldnColAmt;

    private String submit;

    private String update;

}
