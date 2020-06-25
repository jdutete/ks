package com.jdutete.ks.springcamel;

import com.jdutete.ks.springcamel.dto.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private AtomicLong ids = new AtomicLong();
    private Map<Long, User> users = new HashMap<>();

    public User addUser(User user){
        user.setId(ids.incrementAndGet());
        users.put(user.getId(), user);
        return user;
    }

    public Optional<User> get(long id){
        return Optional.ofNullable(users.get(id));
    }
}
