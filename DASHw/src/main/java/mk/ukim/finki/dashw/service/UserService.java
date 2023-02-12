package mk.ukim.finki.dashw.service;

import mk.ukim.finki.dashw.model.User;
import mk.ukim.finki.dashw.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
public interface UserService extends UserDetailsService {


    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
    List<User> findAll();
    User findByUserName(String username);
}
