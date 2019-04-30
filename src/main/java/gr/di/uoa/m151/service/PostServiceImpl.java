package gr.di.uoa.m151.service;

import gr.di.uoa.m151.entity.AppUser;
import gr.di.uoa.m151.entity.Post;
import gr.di.uoa.m151.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class PostServiceImpl {

    final private PostRepository postRepository;
    final private AppUserServiceImpl appUserService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, AppUserServiceImpl appUserService) {
        this.postRepository = postRepository;
        this.appUserService = appUserService;
    }

    public Post addTempPost(){
        Post post = new Post();
        post.setPostText("This is the first dummy post!");
        post.setCreationDate(new Timestamp(new Date().getTime()));

        AppUser appUser = appUserService.findUser(25044L);
        post.setAppUser(appUser);

        return postRepository.save(post);
    }

    public Post findPostById(Long id){
        return postRepository.findById(id).orElse(null);
    }

    public Post addReaction(Long userId, Long postId){
        Post post = findPostById(postId);
        AppUser appUser = appUserService.findUser(userId);
        post.addUserReaction(appUser);
        return postRepository.save(post);
    }
}
