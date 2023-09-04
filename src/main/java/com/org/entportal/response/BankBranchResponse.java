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
public class BankBranchResponse {

    private String msg;
    private String msg1;
    private String msg2;
    private List c1;
    private List c2;

}
