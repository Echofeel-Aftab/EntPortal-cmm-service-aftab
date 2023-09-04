package com.org.entportal.response;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DeleteDataColEntryResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String vreturn;

}