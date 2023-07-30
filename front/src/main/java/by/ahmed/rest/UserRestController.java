package by.ahmed.rest;

import by.ahmed.dto.UserDto;
import by.ahmed.entity.Role;
import by.ahmed.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestParam String firstname,
                           @RequestParam String lastname,
                           @RequestParam String email) {
        UserDto user = new UserDto(firstname, lastname, email, Role.CUSTOMER);
        return userService.create(user);
    }

    @PostMapping("/{userId}/edit")
    public UserDto editUser(@PathVariable("userId") Long id,
                             @RequestParam String firstname,
                             @RequestParam String lastname,
                             @RequestParam String email) {
        UserDto user = new UserDto(firstname, lastname, email, Role.CUSTOMER);
        return userService.update(id, user);
    }
}
