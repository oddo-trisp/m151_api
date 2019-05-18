package gr.di.uoa.m151.repo;

import gr.di.uoa.m151.entity.AppUserShort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppUserShortRepository extends CrudRepository<AppUserShort,Long> {

    @Query(value = "select au.id, au.email , au.full_name, au.user_image from app_user au\n" +
            "where au.id not in (\n" +
            "    select f.following_user_id from app_user a\n" +
            "    join follow f on f.main_user_id = a.id\n" +
            "    where a.email = ?1 \n" +
            ") and au.email != ?1 \n" +
            "order by au.id desc\n" +
            "limit 16;", nativeQuery = true)
    List<AppUserShort> findSuggestions(String email);
}
