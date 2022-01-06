package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.entity.User;
import de.othr.sw.paymentServiceProvider.repository.UserRepo;
import de.othr.sw.paymentServiceProvider.service.ServiceException;
import de.othr.sw.paymentServiceProvider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new ServiceException("User with ")
        );
    }

    @Override
    public User registerUser(User newUser) {
        // TODO: newUser.setPassword noch mit Security-Utils "hashen" passwordEncoder
        return userRepo.save(newUser);
    }
}
