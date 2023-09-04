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
public class UserAccessResponse {
    String mcempregion;
    String mcemparea;
    String mcbranch;
    String mclocbranch;
    List cur;
    List cur1;
    List cur2;

}
