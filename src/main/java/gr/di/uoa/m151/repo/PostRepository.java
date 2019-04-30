package gr.di.uoa.m151.repo;

import gr.di.uoa.m151.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
