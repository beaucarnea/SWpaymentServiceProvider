package de.othr.sw.paymentServiceProvider.service;

import de.othr.sw.paymentServiceProvider.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserById(String email); //throws exception...
    User registerUser(User newUser);
}
