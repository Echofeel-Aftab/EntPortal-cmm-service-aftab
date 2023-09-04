package com.org.entportal.response;

import lombok.*;

import javax.validation.Valid;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class GenerateDepositSlipResponse {

    private List dropdown_pcsubtype;
    private List dropdown_pcbankcddsp;
    private List cur_gendpo2;
    private List cur_gendpo1;
    private List cur_gendpo;
    private String mcbranch;
    private String mcarea;
}
