package StaySure.Services;

import org.springframework.stereotype.Service;
import StaySure.Repositories.DTO.UserDTO;
import StaySure.Repositories.Entitys.User;
import StaySure.Repositories.Mappers.UserMapper;
import StaySure.Repositories.DAO.UserDao;

@Service
public class UserService {
    private UserDao dao;

    public UserService() {
        this.dao = new UserDao();
    }

    public UserDTO createUser(UserDTO new_userdto) {
        User new_user = UserMapper.mapToUser(new_userdto);
        User saved_user = dao.save(new_user);
        if (saved_user != null) {
            return UserMapper.mapToUserDTO(saved_user);
        }
        return null;
    }

    public UserDTO updateUser(UserDTO new_user) {
        User user = UserMapper.mapToUser(new_user);
        User updated_user = dao.update(user);
        if (updated_user != null) {
            return UserMapper.mapToUserDTO(updated_user);
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        return dao.delete(id);
    }

    public UserDTO findUserByID(Long id) {
        User user = dao.findById(id);
        if (user != null) {
            return UserMapper.mapToUserDTO(user);
        }
        return null;
    }

    public UserDTO findUserByEmail(String email) {
        User user = dao.findByEmail(email);
        if (user != null) {
            return UserMapper.mapToUserDTO(user);
        }
        return null;
    }
}