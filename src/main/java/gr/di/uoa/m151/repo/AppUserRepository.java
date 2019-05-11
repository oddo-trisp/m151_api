package gr.di.uoa.m151.repo;

import gr.di.uoa.m151.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser,Long> {
    Optional<AppUser> findAppUserByEmail(String email);
}
