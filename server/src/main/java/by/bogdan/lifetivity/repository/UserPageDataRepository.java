package by.bogdan.lifetivity.repository;

import by.bogdan.lifetivity.model.UserPageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserPageDataRepository extends JpaRepository<UserPageData, Long> {

    @Query("select data from UserPageData data where data.user.id = :id")
    UserPageData getUserPageData(@Param("id") long id);

    UserPageData getByUserId(long userId);
}
