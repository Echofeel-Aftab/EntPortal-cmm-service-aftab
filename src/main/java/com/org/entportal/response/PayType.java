package com.org.entportal.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayType {
    String CPAYTYPE;
    String CPAYDESC;
}
