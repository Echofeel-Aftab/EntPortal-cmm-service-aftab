package com.org.entportal.response.report.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.jackson.JsonComponent;

import com.org.entportal.response.UserListingResponse;

@JsonComponent
public class UserListingResponseProcessor {

	public UserListingResponse userListing(Map response) {
		UserListingResponse userListingResponse = new UserListingResponse();
		List mcRegion = (ArrayList) response.get("mcRegion");
		List role = (ArrayList) response.get("Role");
		List expDate = (ArrayList) response.get("ExpDate");

		userListingResponse.setMcRegion(mcRegion);
		userListingResponse.setRole(role);
		userListingResponse.setExpDate(expDate);

		return userListingResponse;
	}
}
