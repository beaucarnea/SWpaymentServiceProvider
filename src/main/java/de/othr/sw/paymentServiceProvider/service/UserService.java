package de.othr.sw.paymentServiceProvider.service;

import de.othr.sw.paymentServiceProvider.entity.Club;
import de.othr.sw.paymentServiceProvider.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserByEmail(String email);
    User registerUser(User newUser);
    Club registerClub (Club newClub);
    void deleteUser();
}
