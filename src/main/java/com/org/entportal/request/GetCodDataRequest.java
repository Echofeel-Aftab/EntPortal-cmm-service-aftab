package com.org.entportal.request;

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
public class GetCodDataRequest {

	private String mdstartdt;
	private String mdenddt;
	private String mcflg;
	private String msubmit;
	private String mcpaytype;
	private String mcpayslipno;
	private String mcpancard;
	private String mdagedate;
	private String mdawblist;

}
