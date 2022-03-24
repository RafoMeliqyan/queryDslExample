package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getUserById/{id}")
    public User byId(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping("/getUserByName/{name}")
    public User byName(@PathVariable("name") String name) {
        return userService.getByName(name);
    }

    @GetMapping("/getUserByDis/{dis}")
    public List<User> byDisabled(@PathVariable("dis") boolean disabled) {
        return userService.getByDisabled(disabled);
    }

    @PutMapping("/updateUser/{id}")
    public void update(@RequestBody User user, @PathVariable("id") Long id) {
        userService.updateUser(user, id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
