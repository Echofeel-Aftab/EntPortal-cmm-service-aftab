package com.org.entportal.request;

import lombok.*;
import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class SessionflagRequest {
    private String mcemplcode;
    private String mcpassword;
    private String pcscrcd;
    private String mclevel;
    private String msubmit;
}
