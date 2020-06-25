package com.jdutete.ks.springcamel.beans;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class HeaderTest {
    @Handler
    public void hedaers(@Headers Map headers){
        log.info("Headers: {}", headers);
    }
}
