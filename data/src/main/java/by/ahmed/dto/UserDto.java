package by.ahmed.dto;

import by.ahmed.entity.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    String firstname;
    String lastname;
    String email;
    Role role;
}
