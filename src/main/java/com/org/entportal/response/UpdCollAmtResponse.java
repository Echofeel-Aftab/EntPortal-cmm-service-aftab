package com.org.entportal.response;

import lombok.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class UpdCollAmtResponse {

    private BigDecimal pncolamt;
    private BigDecimal poldncolamt;
    private BigDecimal ln_colamt;
    private String lv_cprodcode2;
    private String lv_cmode;
    private String lv_cstatcode;
    private String retText;

}
