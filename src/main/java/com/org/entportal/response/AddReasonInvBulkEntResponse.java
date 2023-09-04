package com.org.entportal.response;

import lombok.*;

import javax.validation.Valid;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class AddReasonInvBulkEntResponse {

    List<String> mcreasoncd;
    List<String> shorts;
    String retText;

}
