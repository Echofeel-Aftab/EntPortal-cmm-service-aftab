package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class EntryScreenInvRequest {

    private String area;
    private String custcode;
    private String invoice;
    private String colldt;
    private String colltype;
    private String paytype;
    private String chqno;
    private String chqdt;
    private String banknm;
    private String branchnm;
    private String submit;

}
