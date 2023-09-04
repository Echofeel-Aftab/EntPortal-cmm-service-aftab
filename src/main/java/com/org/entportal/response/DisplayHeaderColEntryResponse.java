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
public class DisplayHeaderColEntryResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private List collheader;
    private List colldetail;
    private List pccolltype;
    private List pcpaytype;
}