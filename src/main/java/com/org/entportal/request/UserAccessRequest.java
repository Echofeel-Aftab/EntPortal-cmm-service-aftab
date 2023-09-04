package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class UserAccessRequest {

    private String cclocCode;
    private String cdcrRole;
    private String mcemplcode;

}
