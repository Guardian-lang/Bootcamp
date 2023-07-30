package by.ahmed.mapper;

import by.ahmed.dto.UserDto;
import by.ahmed.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toUser(UserDto dto);
}
