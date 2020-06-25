package com.jdutete.ks.springcamel;

import com.jdutete.ks.springcamel.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
@Slf4j
public class MyController {

    private final UserService userService;

    public MyController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> addUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
        log.info("Saving user {}", user);
        User saved = userService.addUser(user);
        UriComponents uriComponents =
                uriComponentsBuilder.path("/{id}").buildAndExpand(saved.getId());
        return ResponseEntity.created(uriComponents.toUri()).body(user);
    }

    @GetMapping(value = "/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE  })
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        log.info("Getting user for id {}", id);
        Optional<User> user = userService.get(id);
        log.info("Got user {}", user);
        return user
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
