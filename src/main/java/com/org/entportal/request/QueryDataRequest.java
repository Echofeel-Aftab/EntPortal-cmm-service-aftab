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
public class QueryDataRequest {

    private String pdquerydt1;
    private String pcgen;
    private String pcawbno;
    private String mccollempcd;
    private String mccollloccd;
    private String mcdcrrole;
    private String pregion;
    private List<String> pclvlarea;

}
