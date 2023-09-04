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
public class AddReasonInvBulkEntRequest {

    private String entryno;
    private String billno;
    private String outamt;
    private String collamt;
    private String tdsamt;
    private String shortid;
    private String pcreasoncd;
    private List<String> mcreasoncd;
    private List<String> shortcoll;
    private String submit;

}
