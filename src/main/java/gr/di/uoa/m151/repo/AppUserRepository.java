package gr.di.uoa.m151.repo;

import gr.di.uoa.m151.entity.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser,Long> {
    Optional<AppUser> findAppUserByEmail(String email);


    @Query(value = "select au.id, au.full_name, au.user_image, au.email, au.enabled, au.encrypted_password,au.password from app_user au", nativeQuery = true)
    List<AppUser> findSuggestions(String email);
}
