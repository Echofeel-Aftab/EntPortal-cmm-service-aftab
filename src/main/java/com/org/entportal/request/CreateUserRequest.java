package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class CreateUserRequest {

    private List<String> pcemplcode;
    private List<String> pcrole;
    private List<String> pdexpitydt;
    private List<String> pclocaccess;
    private String pcrole1;
    private String pcrole2;
    private String pcrole3;
    private String pcrole4;
    private String pcrole5;
    private String pcrole6;
    private String pcrole7;
    private String pcrole8;
    private String pcrole9;
    private String pcrolea;
    private String pcroleb;
    private String pcrolec;
    private String pcroled;
    private String pcrolee;
    private String pclose;
    private String psubmit;
    private String mclogempcd;

}
