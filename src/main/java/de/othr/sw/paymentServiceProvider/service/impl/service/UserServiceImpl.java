package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.entity.Club;
import de.othr.sw.paymentServiceProvider.entity.User;
import de.othr.sw.paymentServiceProvider.repository.AccountRepo;
import de.othr.sw.paymentServiceProvider.repository.AddressRepo;
import de.othr.sw.paymentServiceProvider.repository.ClubRepo;
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
    private ClubRepo clubRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(
                () -> new ServiceException("User with email " + userId + " not found")
        );
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findUserByEmailContaining(email).orElseThrow(
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
    public Club registerClub(Club newClub) {
        newClub.setPassword(passwordEncoder.encode(newClub.getPassword()));
        addressRepo.save(newClub.getAddress());
        accountRepo.save(newClub.getAccount());
        return clubRepo.save(newClub);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserByEmailContaining(username).orElseThrow(
                () -> new UsernameNotFoundException("User with email " + username + " not found")
        );
    }
}
