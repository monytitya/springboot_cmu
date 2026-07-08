package Springboot_cmu.Usermanage_services.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Springboot_cmu.Usermanage_services.Dto.UserDto;
import Springboot_cmu.Usermanage_services.Model.User;
import Springboot_cmu.Usermanage_services.Services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @GetMapping("/active-adults")
    public ResponseEntity<List<UserDto>> getActiveAdultUsers() {
        return ResponseEntity.ok(userService.findActiveAdultUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email)
                .map(user -> ResponseEntity.ok("Found user: " + user.getName()))
                .orElseGet(() -> ResponseEntity.ok("The " + email + " is not found."));
    }

    @GetMapping("/sorted-by-age")
    public ResponseEntity<List<User>> getUsersSortedByAge() {
        return ResponseEntity.ok(userService.getUsersSortedByAgeDesc());
    }
}