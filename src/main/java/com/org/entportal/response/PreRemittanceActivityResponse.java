package com.org.entportal.response;

import lombok.*;

import javax.validation.Valid;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class PreRemittanceActivityResponse {

    List c1;
    List c2;
    List curErr;
    private String psuccess;
    private BigDecimal ln_count1;
    private BigDecimal ln_count2;
    private String retVal;

}
