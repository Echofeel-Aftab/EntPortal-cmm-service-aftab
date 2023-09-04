package com.org.entportal.request;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DeleteDataColEntryRequest implements Serializable {

    private String pcserialno;

}

