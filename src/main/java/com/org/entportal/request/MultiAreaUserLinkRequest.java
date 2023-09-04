package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class MultiAreaUserLinkRequest {

    private String emplcode;
    private String area;
    private String chk;
    private String submit;
    private String reset;

}
