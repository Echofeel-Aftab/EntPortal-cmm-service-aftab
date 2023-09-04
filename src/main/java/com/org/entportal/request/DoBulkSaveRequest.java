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
public class DoBulkSaveRequest {

    private List<String> pcawbno;
    private List<String> pdpudeldt;
    private List<String> pcpaytype;
    private List<String> pncollval;
    private List<String> pncollamt;
    private List<String> pntdsamt;
    private List<String> pccashmemno;
    private List<String> pccollmd;
    private List<String> pcchk;
    private List<String> pcrmks;
    private List<String> pcchkno;
    private List<String> pdchkdt;
    private List<String> pcbnknm;
    private List<String> pcbrnnm;
    private List<String> pcprodcode;
    private List<String> pcorgscrcd;
    private List<String> pcoutamt;
    private List<String> pcchk1;
    private List<String> pccollval1;
    private String pcpudelemplcd;
    private String pdcolldt;
    private String allchk;
    private String pctranid;
    private String pgsaarea;
    private String pgsacode;
    private String mccollloccd;
    private String mccollempcd;

    private String mccloccode;
    private String mcdcrrole;
    //private List<String> rettext;
}
