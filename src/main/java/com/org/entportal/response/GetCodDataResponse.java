package com.org.entportal.response;

import java.util.List;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class GetCodDataResponse {

    private List mcpaytype1;
    private List c1;
    private List c2;
    private List c4;
    private List strdata;
	
}
