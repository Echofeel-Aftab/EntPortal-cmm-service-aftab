package com.org.entportal.spcall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class StoredProcedureCall {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Map callSProc(SimpleJdbcCall sjdbccall, SqlParameterSource sps, String rs) {
        logger.info(" callSProc Entry=CatalogName=>" + sjdbccall.getCatalogName() + "ProcedureName=" + sjdbccall.getProcedureName());
        Optional<Map> map;
        Map response = null;
        try {
        	 logger.info("SqlParameterSource: {}",sps);
            if (sps == null) {
                map = Optional.ofNullable(sjdbccall.execute());
            } else {
                map = Optional.ofNullable(sjdbccall.execute(sps));
            }
            if (map.isPresent()) {
                response = map.get();
            }

        } catch (Exception e) {
        	logger.error(e.getMessage());
        }
        logger.info(" callSProc Exit " + response);
        return response;

    }

    /*
     * public Optional<Object> callPrcedurejdbcTemplate(JdbcTemplate
     * jdbccall,SqlParameterSource sps){
     *
     * jdbccall.execute(null, null) jdbccall.exe
     *
     *
     *
     * }
     */

}
