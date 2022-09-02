package com.example.springrest.service;

import com.example.springrest.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RepositoryStubService {

    final private AtomicLong counter = new AtomicLong();
    final private Map<Long, User> userRepository = new HashMap<>();

    {
        saveUser(counter.incrementAndGet(),
                User.builder().id(counter.get())
                        .firstName("John")
                        .secondName("Smith")
                        .department("IT")
                        .position("Administrator")
                        .build());
    }

    public List<User> findAll() {
        return List.copyOf(userRepository.values());
    }

    public User findUserById(Long id) {
        return userRepository.get(id);
    }

    public User saveUser(Long id, User newUser) {
        userRepository.put(id, newUser);
        return newUser;
    }

    public User saveUser(User newUser) {
        Long id = counter.incrementAndGet();
        newUser.setId(id);
        userRepository.put(id, newUser);
        return newUser;
    }

    public void deleteById(Long id) {
        userRepository.remove(id);
    }

}
