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
public class EntryScreenResponse {

    private BigDecimal inCount;

    private BigDecimal custpayamt;

    private List collMd;

    private List outstnd1;

    private List outstnd;

    private String mcemplname;


}
