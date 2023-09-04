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
public class BulkUploadResponse {

    private List<CollectionType> collType;

    private List<PayType> payType;
}
