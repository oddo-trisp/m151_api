package gr.di.uoa.m151.service;

import gr.di.uoa.m151.entity.*;
import gr.di.uoa.m151.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl {

    final private PostRepository postRepository;
    final private AppUserServiceImpl appUserService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, AppUserServiceImpl appUserService) {
        this.postRepository = postRepository;
        this.appUserService = appUserService;
    }

    public Post findPostById(Long id){
        return postRepository.findById(id).orElse(null);
    }


    public Post addUserPostReaction(String email, Long postId, UserPostReaction userPostReaction){
        AppUser appUser = appUserService.findAppUserByEmail(email);
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null || appUser == null) return null;

        userPostReaction.setAppUser(appUser);
        post.addUserPostReaction(userPostReaction);
        Post persistedPost = postRepository.save(post);
        persistedPost.getUserReactions().forEach(r -> {
            if(r.getReactionType() == null)
                r.init();
        });
        return persistedPost;
    }

    public Post removeUserPostReaction(String email, Long postId, Long likeId){
        AppUser appUser = appUserService.findAppUserByEmail(email);
        Post post = postRepository.findById(postId).orElse(null);
        if(post == null || appUser == null) return null;

        post.removeUserReaction(appUser, likeId);
        Post persistedPost = postRepository.save(post);
        persistedPost.getUserReactions().forEach(r -> {
            if(r.getReactionType() == null)
                r.init();
        });
        return persistedPost;
    }

    public List<Post> findRecentPosts(String email){
        return postRepository.findRecentPosts(email);
    }
}
