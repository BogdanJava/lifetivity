package by.bogdan.lifetivity.repository;

import by.bogdan.lifetivity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
