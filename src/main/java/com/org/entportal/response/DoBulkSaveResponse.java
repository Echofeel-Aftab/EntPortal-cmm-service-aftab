package com.org.entportal.response;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class DoBulkSaveResponse {

    private String retText;

}
