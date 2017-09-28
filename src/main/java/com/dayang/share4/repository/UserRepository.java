package com.dayang.share4.repository;

import com.dayang.share4.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String>{

    public List<User> findByNameContaining(String name);

    public List<User> findAll();
}
