package gr.di.uoa.m151.repo;

import gr.di.uoa.m151.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query(value = "select p.* from app_user au\n" +
            "join post p on p.app_user_id = au.id\n" +
            "where au.id in (\n" +
            "    select f.following_user_id from app_user a\n" +
            "    join follow f on f.main_user_id = a.id\n" +
            "    where a.email = ?1 \n" +
            ")\n" +
            "order by p.creation_date desc\n" +
            "limit 20", nativeQuery = true)
    List<Post> findRecentPosts(String email);

}
