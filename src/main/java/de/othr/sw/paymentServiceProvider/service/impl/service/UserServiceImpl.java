package de.othr.sw.paymentServiceProvider.service.impl.service;

import de.othr.sw.paymentServiceProvider.entity.Club;
import de.othr.sw.paymentServiceProvider.entity.User;
import de.othr.sw.paymentServiceProvider.repository.AccountRepo;
//import de.othr.sw.paymentServiceProvider.repository.AddressRepo;
import de.othr.sw.paymentServiceProvider.repository.ClubRepo;
import de.othr.sw.paymentServiceProvider.repository.PaymentRepo;
import de.othr.sw.paymentServiceProvider.repository.UserRepo;
import de.othr.sw.paymentServiceProvider.service.ServiceException;
import de.othr.sw.paymentServiceProvider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Scope(scopeName = "singleton")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ClubRepo clubRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PaymentRepo paymentRepo;

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
        Optional<User> user = userRepo.findUserByEmail(newUser.getEmail());
        if(user.isPresent()){
            return null;
        }
        try{
            return userRepo.save(newUser);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }

    }

    @Override
    public Club registerClub(Club newClub) {
        newClub.setPassword(passwordEncoder.encode(newClub.getPassword()));
        Optional<Club> club = clubRepo.findClubByEmail(newClub.getEmail());
        if(club.isPresent()){
            return null;
        }
        try{
            return clubRepo.save(newClub);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserByEmailContaining(username).orElseThrow(
                () -> new UsernameNotFoundException("User with email " + username + " not found")
        );
    }

    @Override
    @Transactional
    public void deleteUser(){
        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        paymentRepo.deleteAllByReceiverAccount(thisUser.getAccount());
        paymentRepo.deleteAllBySenderAccount(thisUser.getAccount());
        accountRepo.delete(thisUser.getAccount());
        userRepo.delete(thisUser);
    }
}
