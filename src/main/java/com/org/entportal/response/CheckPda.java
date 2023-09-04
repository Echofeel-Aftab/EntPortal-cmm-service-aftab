package com.org.entportal.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckPda {
    private String CPRODCODE;
    private String CORGSCRCD;
    private String NAMT;
    private String NACTAMTTRF;
}
