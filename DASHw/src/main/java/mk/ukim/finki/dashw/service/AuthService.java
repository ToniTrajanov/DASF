package mk.ukim.finki.dashw.service;

import mk.ukim.finki.dashw.model.User;


public interface AuthService {
    User login(String username, String password);
}

