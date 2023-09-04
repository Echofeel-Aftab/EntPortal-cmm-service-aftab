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
public class SaveApprovalRequest {
    private List<String> pcpayslipno;
    private List<String> pdpayslipdt;
    private List<String> pcchk;
    private String allchk;
    private String pcapprovedby;
    private String mccollloccd;
    private String mccollempcd;

}
