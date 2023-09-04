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
public class CustWalletLedgerRequest {

	private String morgarea;
	private String mcustcode;
	private String mfromdt;
	private String mtodt;
	private String msubmit;
	
}
