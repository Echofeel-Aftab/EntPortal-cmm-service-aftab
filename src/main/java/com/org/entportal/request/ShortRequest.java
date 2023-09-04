package com.org.entportal.request;
import lombok.*;
import javax.validation.Valid;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class ShortRequest {
	private String pcdetailsrNo;
}
