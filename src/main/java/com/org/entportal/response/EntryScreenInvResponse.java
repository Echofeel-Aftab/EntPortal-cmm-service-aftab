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
public class EntryScreenInvResponse {

    private String custname;
    private String custcode1;
    private List outstnd;
    private List colltype1;
    private List paytype1;

}
