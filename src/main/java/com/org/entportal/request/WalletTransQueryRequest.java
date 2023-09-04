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
public class WalletTransQueryRequest {

	private String mcstartdate;
	private String mcenddate;
	private String mregion;
	private String psubmit;

}
