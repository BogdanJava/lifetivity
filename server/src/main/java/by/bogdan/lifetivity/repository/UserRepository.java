package by.bogdan.lifetivity.repository;

import by.bogdan.lifetivity.model.User;
import by.bogdan.lifetivity.model.UserPageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

}
