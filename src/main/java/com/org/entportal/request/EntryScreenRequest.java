package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class EntryScreenRequest {

    private String awbno;
    private String fromdt;
    private String emplcode;
    private String todt;
    private String submit;
    private String saarea;
    private String sacode;

}
