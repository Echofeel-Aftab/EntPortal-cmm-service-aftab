package com.org.entportal.request;

import io.github.millij.poi.ss.model.annotations.Sheet;
import io.github.millij.poi.ss.model.annotations.SheetColumn;
import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
@Sheet
public class GetValFromUploadRequest {

    @SheetColumn("AWBNO")
    private String awbno;
    @SheetColumn("AMOUNT")
    private String collamt;
    @SheetColumn("REMARKS")
    private String remarks;
    @SheetColumn("CHEQUE NO")
    private String chequeno;
    @SheetColumn("CHEQUE DATE")
    private String cheqdate;
    private String emplcode;
    private String scrcd;
    private String collrcptdt;
    private String colltype;
    private String paytype;

}
