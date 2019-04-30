package gr.di.uoa.m151.controller;

import gr.di.uoa.m151.entity.AppUser;
import gr.di.uoa.m151.entity.Post;
import gr.di.uoa.m151.entity.UserPostReaction;
import gr.di.uoa.m151.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    final private PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/add_temp_post", method = RequestMethod.GET)
    public String addPost() {
        return postService.addTempPost().toString();
    }

    @RequestMapping(value = "/get_reactions", method = RequestMethod.GET)
    public List<UserPostReaction> addReactions() {
        Post post = postService.findPostById(4L);
        return post.getUserReactions();
    }

    @RequestMapping(value = "/add_reaction", method = RequestMethod.GET)
    public Post addReaction() {
      return postService.addReaction(25042L, 4L);
    }
}
