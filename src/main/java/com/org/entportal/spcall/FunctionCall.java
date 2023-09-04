package com.org.entportal.spcall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FunctionCall {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Object callFunctionForObject(SimpleJdbcCall sjdbccall, SqlParameterSource parameters) {
        logger.info(" Function Entry=CatalogName=>" + sjdbccall.getCatalogName() + "FunctionName=" + sjdbccall.getProcedureName());
        Object response = null;
        try {
            response = Optional.ofNullable(sjdbccall.executeFunction(String.class, parameters));

        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(" callFunction Exit" + response);
        return response;
    }
}
