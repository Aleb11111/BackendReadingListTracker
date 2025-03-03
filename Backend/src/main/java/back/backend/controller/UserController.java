
package back.backend.controller;

import back.backend.exceptions.ResourceNotFoundException;
import back.backend.model.User;
import back.backend.repository.UserRepository;
import back.backend.tokens.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import java.util.Collections;
import java.util.Objects;

@RestController
public class UserController {
    private UserRepository userRepository;
    private JWTUtil jwtUtil;

    public UserController(UserRepository userRepository, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    void registerUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @PostMapping("/login")
    ResponseEntity<?> logUser(@RequestBody User user) {
        for (User u : userRepository.findAll()) {
            if (Objects.equals(u.getUsername(), user.getUsername()) &&
                    Objects.equals(u.getEmail(), user.getEmail()) &&
                    Objects.equals(u.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getUsername());
                return ResponseEntity.ok().body(Collections.singletonMap("token", token));
            }
        }
        throw new ResourceNotFoundException("User not found");
    }
}

//package back.backend.controller;
//
//import back.backend.model.User;
//import back.backend.model.UserShelf;
//import back.backend.repository.ShelfRepository;
//import back.backend.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ShelfRepository shelfRepository;
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        User user = userRepository.findById(id);
//        if (user != null) {
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Void> createUser(@RequestBody User user) {
//        userRepository.save(user);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody User user) {
//        User existingUser = userRepository.findById(id);
//        if (existingUser != null) {
//            user.setId(id); // Ensure the ID in the request body is set
//            userRepository.update(user);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        User existingUser = userRepository.findById(id);
//        if (existingUser != null) {
//            userRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}
