package gr.di.uoa.m151.repo;

import gr.di.uoa.m151.entity.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser,Long> {
    Optional<AppUser> findAppUserByEmail(String email);

    @Query(value = "select * from app_user au\n" +
            "where au.id not in (\n" +
            "    select f.following_user_id from app_user a\n" +
            "    join follow f on f.main_user_id = a.id\n" +
            "    where a.email = ?1 \n" +
            ") and au.email != ?1 \n" +
            "order by au.id desc\n" +
            "limit 20;", nativeQuery = true)
    List<AppUser> findSuggestions(String email);
}
