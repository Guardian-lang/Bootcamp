package by.ahmed.mapper;

import by.ahmed.dto.UserDto;
import by.ahmed.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserListMapper {
    List<UserDto> toDtoList(List<User> users);
    List<User> toUserList(List<UserDto> dtos);
}
