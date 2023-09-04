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
public class AddActionPaySlipResponse {
    private String mccustname;
    private List short1;
    private List detail;
    private List detail_temp;
    private List pcreasoncd;
}
