package com.dayang.share4.controller;

import com.dayang.share4.entities.User;
import com.dayang.share4.repository.DynamicQuery;
import com.dayang.share4.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private DynamicQuery dynamicQuery;

    @PostMapping("/users/{userId}")
    public ResponseEntity saveUser(@RequestBody User user,
                                   @PathVariable String userId) {

        user.setId(userId);
        userRepository.save(user);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity getUserById(@PathVariable String userId) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            return ResponseEntity
                    .ok(user);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("info", "该用户不存在"));
        }
    }

    @GetMapping("/users")
    public ResponseEntity getUsers(@RequestParam(required = false) String name) {
        List<User> users;
        if (name != null) {
            users = userRepository.findByNameContaining(name);
        } else {
            users = userRepository.findAll();
        }

        return ResponseEntity
                .ok(users);
    }

    @GetMapping("/users/byHQL")
    public ResponseEntity getUsersByHQL(@RequestParam(required = false) String name) {
        String hql = "from User u";
        if (name != null) {
            hql += " where u.name like :name";
        }
        TypedQuery<User> typedQuery = dynamicQuery.executeHql(hql, User.class);
        if (name != null) {
            typedQuery.setParameter("name", "%" + name + "%");
        }
        List<User> users = typedQuery.getResultList();

        return ResponseEntity
                .ok(users);
    }
}
