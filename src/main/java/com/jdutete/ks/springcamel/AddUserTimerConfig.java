package com.jdutete.ks.springcamel;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ks.timer.add-user")
@Data
public class AddUserTimerConfig {

    private long delay;
    private boolean fixedRate;
    private long period;
}
