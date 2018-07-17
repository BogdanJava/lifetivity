package by.bogdan.lifetivity.repository;

import by.bogdan.lifetivity.model.ContactInfo;
import by.bogdan.lifetivity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select user.contactInfo from User user where user.id = :userId")
    ContactInfo getUserContactInfo(@Param("userId") long userId);
}
