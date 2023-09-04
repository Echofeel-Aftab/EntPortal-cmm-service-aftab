package com.org.entportal.response;

import lombok.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class QueryDataResponse {

    private List<Map<String, String>> coll_detl = new ArrayList<>();
    private List<Object> retstat;
}
