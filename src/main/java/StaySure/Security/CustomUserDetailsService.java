package StaySure.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import StaySure.Repositories.DAO.CheckerDao;
import StaySure.Repositories.DAO.UserDao;
import StaySure.Repositories.DAO.AdminDao;
import StaySure.Repositories.DAO.LesseDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Autowired
    AdminDao adminDao;
    @Autowired
    LesseDao lesseDao;
    @Autowired
    CheckerDao checkerDao;

    public CustomUserDetailsService(UserDao userDao, AdminDao adminDao, LesseDao lesseDao, CheckerDao checkerDao) {
        this.userDao = userDao;
        this.adminDao = adminDao;
        this.lesseDao = lesseDao;
        this.checkerDao = checkerDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = findUserDetailsByEmail(email);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userDetails;
    }

    private UserDetails findUserDetailsByEmail(String email) {
        StaySure.Repositories.Entitys.User user = userDao.findByEmail(email);
        if (user != null) {
            return new User(user.getEmail(), user.getPassword(), List.of());
        }

        StaySure.Repositories.Entitys.Admin admin = adminDao.findByEmail(email);
        if (admin != null) {
            return new User(admin.getEmail(), admin.getPassword(), List.of());
        }

        StaySure.Repositories.Entitys.Lesse lesse = lesseDao.findByEmail(email);
        if (lesse != null) {
            return new User(lesse.getEmail(), lesse.getPassword(), List.of());
        }

        StaySure.Repositories.Entitys.Checker checker = checkerDao.findByEmail(email);
        if (checker != null) {
            return new User(checker.getEmail(), checker.getPassword(), List.of());
        }

        return null;
    }
}