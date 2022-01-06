package de.othr.sw.paymentServiceProvider.service;

import de.othr.sw.paymentServiceProvider.entity.User;

public interface UserService {
    User getUserById(Long userId); //throws exception...
    User registerUser(User newUser);
}
