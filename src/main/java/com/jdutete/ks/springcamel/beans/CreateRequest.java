package com.jdutete.ks.springcamel.beans;

import com.jdutete.ks.springcamel.dto.Sex;
import com.jdutete.ks.springcamel.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
public class CreateRequest {
    private final AtomicLong counter = new AtomicLong();
    @Handler
    public User createUser(){
        log.info("Creatimg user...");
        long count = counter.incrementAndGet();
        User user = new User();
        user.setName("User" + count);
        user.setSurname("Surname" + count);
        user.setSex(Sex.MALE);
        log.info("User {} created...", user);
        return user;
    }
}
