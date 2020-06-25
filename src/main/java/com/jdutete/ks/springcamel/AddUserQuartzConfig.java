package com.jdutete.ks.springcamel;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ks.quartz.add-user")
@Data
public class AddUserQuartzConfig {

    private String cron;
}
