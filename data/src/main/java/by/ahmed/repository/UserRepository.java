package by.ahmed.repository;

import by.ahmed.entity.Role;
import by.ahmed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "order by u.email")
    List<User> findAllOrderByEmail();
    List<User> findAllByFirstnameOrderByEmail(String firstname);
    List<User> findAllByLastnameOrderByEmail(String lastname);
    Optional<User> findByFirstnameAndLastnameOrderByEmail(String firstname, String lastname);
    Optional<User> findByEmailOrderByEmail(String email);
    List<User> findAllByRoleOrderByEmail(Role role);
}
