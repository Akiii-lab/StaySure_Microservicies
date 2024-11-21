package StaySure.Repositories.Mappers;

import StaySure.Repositories.Entitys.User;
import StaySure.Repositories.Entitys.Factories.UserFactory;
import StaySure.Repositories.DTO.UserDTO;

public class UserMapper {
    public static User mapToUser(UserDTO userDto) {
        return UserFactory.createUser(
                userDto.id,
                userDto.name,
                userDto.lastName,
                userDto.phone,
                userDto.email,
                userDto.identificationNumber,
                userDto.birthDate,
                userDto.notificationsEnabled,
                userDto.password

        );
    }

    public static UserDTO mapToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getPhone(),
                user.getEmail(),
                user.getIdentificationNumber(),
                user.getBirthDate(),
                user.getPassword(),
                user.isNotificationsEnabled());
    }
}