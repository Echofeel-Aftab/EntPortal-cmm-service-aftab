package com.org.entportal.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DepositApprovalReportRequest {

	private String pcregion;
	private String pcolloc;
	private String pdfromdt;
	private String pdtodt;
	private String psubmit;

}
