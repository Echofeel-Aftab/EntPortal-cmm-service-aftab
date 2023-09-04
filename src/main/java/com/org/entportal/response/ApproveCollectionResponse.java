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
public class ApproveCollectionResponse {

    List c1;
    //List c2;
    String retText;
    String mcdelflg;
}
