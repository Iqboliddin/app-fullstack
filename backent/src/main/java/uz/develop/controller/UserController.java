package uz.develop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.develop.entity.UserEntity;
import uz.develop.payload.ApiResponse;
import uz.develop.repository.UserRepository;
import uz.develop.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public HttpEntity<?> getUsers() {
        ApiResponse apiResponse = userService.getUsers();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getUser(@PathVariable Integer id) {
        UserEntity user = userService.getUser(id);
        return ResponseEntity.accepted().body(user);
    }

    @PostMapping
    public HttpEntity<?> addUser(@RequestBody UserEntity user) {
        ApiResponse apiResponse = userService.addUser(user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @PutMapping()
    public HttpEntity<?> editUser(@RequestBody UserEntity body) {
        ApiResponse apiResponse = userService.editUser(body);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Integer id) {
        ApiResponse apiResponse = userService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }
}
