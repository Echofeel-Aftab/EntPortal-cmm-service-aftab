package com.org.entportal.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AddActionColEntryResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mccustname;
    private String rettext;
    private List detail;
    private List dropdown_pcreasoncd;

}