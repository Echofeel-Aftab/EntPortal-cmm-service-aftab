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
public class UserListingResponse {

	private List mcRegion;
	private List role;
	private List expDate;

}
