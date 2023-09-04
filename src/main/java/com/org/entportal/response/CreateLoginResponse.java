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
public class CreateLoginResponse {

    private List locAccess;
    private List c1;
    private List c2;
    private List role;

}
