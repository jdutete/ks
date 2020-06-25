package com.jdutete.ks.springcamel.beans;

import com.jdutete.ks.springcamel.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class CheckResponse {
    @Handler
    public void response(@Body User user, @Headers Map headers){
        log.info("Response: {}", user);
        log.info("Headers: {}", headers);
    }
}
