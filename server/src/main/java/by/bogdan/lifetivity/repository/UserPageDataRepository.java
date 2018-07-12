package by.bogdan.lifetivity.repository;

import by.bogdan.lifetivity.model.UserPageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPageDataRepository extends JpaRepository<UserPageData, Long> {

    UserPageData getByUserId(long userId);

}
