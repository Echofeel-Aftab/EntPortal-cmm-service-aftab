package com.org.entportal.request;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DisplayHeaderColEntryRequest implements Serializable {

    private String pctranssrno;
    private String pcserialno;
    private String mccollempcd;
    private String mccollloccd;
    private String mcdcrrole;

}

