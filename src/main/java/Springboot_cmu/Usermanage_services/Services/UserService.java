package Springboot_cmu.Usermanage_services.Services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Springboot_cmu.Usermanage_services.Dto.UserDto;
import Springboot_cmu.Usermanage_services.Model.User;

@Service
public class UserService {

    private final List<User> userDatabase = new ArrayList<>(List.of(
            new User("1", "Chanthy", "chanthy@example.com", 28, true),
            new User("2", "Arunortey", "arunortey@example.com", 17, true),
            new User("3", "Mengchun", "mengchun@example.com", 34, false),
            new User("4", "Khatiya", "khatiya@example.com", 41, true)));

    public List<User> getAllUsers() {
        return userDatabase;
    }

    public Optional<User> findById(String id) {
        return userDatabase.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public Optional<User> findByEmail(String email) {
        return userDatabase.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public User createUser(User user) {
        userDatabase.add(user);
        return user;
    }

    public List<UserDto> findActiveAdultUsers() {
        return userDatabase.stream()
                .filter(User::isActive)
                .filter(user -> user.getAge() >= 18)
                .map(user -> new UserDto(user.getName(), user.getEmail().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<User> getUsersSortedByAgeDesc() {
        return userDatabase.stream()
                .sorted(Comparator.comparingInt(User::getAge).reversed())
                .collect(Collectors.toList());
    }
}