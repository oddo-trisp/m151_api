package gr.di.uoa.m151.service;

import gr.di.uoa.m151.entity.AppUser;
import gr.di.uoa.m151.entity.CommentReaction;
import gr.di.uoa.m151.entity.LikeReaction;
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

    public Post addUserPostReaction(String email, Post post){
        AppUser appUser = appUserService.findAppUserByEmail(email);
        LikeReaction likeReaction = new LikeReaction();
        likeReaction.setAppUser(appUser);
        post.addUserPostReaction(likeReaction);
        return postRepository.save(post);
    }

    public Post addCommentReaction(String email, Post post, String commentText){
        AppUser appUser = appUserService.findAppUserByEmail(email);
        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setAppUser(appUser);
        commentReaction.setCommentText(commentText);
        post.addUserPostReaction(commentReaction);
        return postRepository.save(post);
    }
}
