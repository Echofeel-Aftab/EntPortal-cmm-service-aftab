package com.org.entportal.request;

import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class ApproveCollectionRequest {
    private String pcolloc;
    private String pdcolldt;
    private String psubmit;
    private String mccollloccd;
    private String mccollempcd;

}
