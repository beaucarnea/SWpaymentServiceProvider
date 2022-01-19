package de.othr.sw.paymentServiceProvider.repository;

import de.othr.sw.paymentServiceProvider.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// ToDo: Crud, Jpa, PagingAndSorting
@Repository
@Scope(scopeName = "singleton")
public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> findUserByEmailContaining(String email);
    Optional<User> findUserByEmail(String email);
}
