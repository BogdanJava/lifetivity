package by.bogdan.lifetivity.repository.mysql;

import by.bogdan.lifetivity.model.entity.ContactInfo;
import by.bogdan.lifetivity.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

public interface UserRepository extends JpaRepository<User, Long> {
    @Nullable User findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select user.contactInfo from User user where user.id = :userId")
    ContactInfo getUserContactInfo(@Param("userId") long userId);
}
