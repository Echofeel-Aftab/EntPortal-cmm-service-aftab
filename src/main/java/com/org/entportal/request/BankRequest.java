package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class BankRequest {

    private String bankCd;
    private String bankDesc;
    private String acc;
    private String bankType;
    private String notInUse;
    private String submit;
}
