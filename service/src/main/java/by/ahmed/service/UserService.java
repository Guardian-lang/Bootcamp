package by.ahmed.service;

import by.ahmed.dto.UserDto;
import by.ahmed.entity.Role;
import by.ahmed.mapper.UserMapper;
import by.ahmed.mapper.UserUpdateMapper;
import by.ahmed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUpdateMapper userUpdateMapper;

    public List<UserDto> findAll() {
        return userRepository.findAllOrderByEmail().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public List<UserDto> findAllByRole(Role role) {
        return userRepository.findAllByRoleOrderByEmail(role).stream()
                .map(userMapper::toDto)
                .toList();
    }

    public List<UserDto> findAllByFirstname(String firstname) {
        return userRepository.findAllByFirstnameOrderByEmail(firstname).stream()
                .map(userMapper::toDto)
                .toList();
    }

    public List<UserDto> findAllByLastname(String lastname) {
        return userRepository.findAllByLastnameOrderByEmail(lastname).stream()
                .map(userMapper::toDto)
                .toList();
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    public Optional<UserDto> findByFirstnameAndLastname(String firstname, String lastname) {
        return userRepository.findByFirstnameAndLastnameOrderByEmail(firstname, lastname)
                .map(userMapper::toDto);
    }

    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmailOrderByEmail(email)
                .map(userMapper::toDto);
    }

    @Transactional
    public UserDto create(UserDto userDto) {
        return Optional.of(userDto)
                .map(userMapper::toUser)
                .map(user -> {
                    log.info("User is created: {}", userMapper.toDto(user));
                    return userRepository.save(user);})
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public UserDto update(Long id, UserDto userDto) {
        return userRepository.findById(id)
                .map(entity -> {
                    log.info("New user data: {}", userDto);
                    return userUpdateMapper.map(entity, userDto);})
                .map(userRepository::saveAndFlush)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    log.info("User deleted");
                    return true;
                })
                .orElse(false);
    }
}
