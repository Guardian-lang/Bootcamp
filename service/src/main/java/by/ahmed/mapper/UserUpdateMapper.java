package by.ahmed.mapper;

import by.ahmed.dto.UserDto;
import by.ahmed.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserUpdateMapper {

    default User map(User entity, UserDto dto) {
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());

        return entity;
    }
}
