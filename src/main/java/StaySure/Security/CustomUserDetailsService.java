package StaySure.Security;

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

    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDao userDao = new UserDao();
        StaySure.Repositories.Entitys.User user = userDao.findByEmail(email);

        if (user == null) {
            AdminDao adminDao = new AdminDao();
            StaySure.Repositories.Entitys.Admin admin = adminDao.findByEmail(email);

            if (admin == null) {
                LesseDao lesseDao = new LesseDao();
                StaySure.Repositories.Entitys.Lesse leese = lesseDao.findByEmail(email);

                if (leese == null) {
                    CheckerDao checkerDao = new CheckerDao();
                    StaySure.Repositories.Entitys.Checker checker = checkerDao.findByEmail(email);  
                    
                    if (checker == null) {
                        throw new UsernameNotFoundException("User not found");
                    } else {
                        return new User(checker.getEmail(), checker.getPassword(), null);
                    }
                    
                } else {
                    return new User(leese.getEmail(), leese.getPassword(), null);
                }
                
            } else {
                return new User(admin.getEmail(), admin.getPassword(), null);
            }

        } else {
            return new User(user.getEmail(), user.getPassword(), null);
        }

    }
}