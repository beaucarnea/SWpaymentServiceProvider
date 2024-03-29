package de.othr.sw.paymentServiceProvider.repository;

import de.othr.sw.paymentServiceProvider.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
@Scope(scopeName = "singleton")
public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
