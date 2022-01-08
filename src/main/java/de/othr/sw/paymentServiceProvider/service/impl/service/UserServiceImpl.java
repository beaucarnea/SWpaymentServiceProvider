package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.entity.User;
import de.othr.sw.paymentServiceProvider.repository.AccountRepo;
import de.othr.sw.paymentServiceProvider.repository.AddressRepo;
import de.othr.sw.paymentServiceProvider.repository.UserRepo;
import de.othr.sw.paymentServiceProvider.service.ServiceException;
import de.othr.sw.paymentServiceProvider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User getUserById(String email) {
        return userRepo.findById(email).orElseThrow(
                () -> new ServiceException("User with email " + email + " not found")
        );
    }

    @Override
    public User registerUser(User newUser) {

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        addressRepo.save(newUser.getAddress());
        accountRepo.save(newUser.getAccount());
        return userRepo.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findById(username).orElseThrow(
                () -> new UsernameNotFoundException("User with email " + username + " not found")
        );
    }
}
