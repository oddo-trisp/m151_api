package gr.di.uoa.m151.controller;

import gr.di.uoa.m151.entity.CommentReaction;
import gr.di.uoa.m151.entity.Post;
import gr.di.uoa.m151.entity.UserPostReaction;
import gr.di.uoa.m151.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    final private PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/findPostById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Post findPostById(@RequestParam Long id) {
        return postService.findPostById(id);
    }

    @RequestMapping(value = "/addCommentReaction", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Post addCommentReaction(@RequestParam String email, @RequestParam String postId,@RequestBody CommentReaction commentReaction) {
        return postService.addUserPostReaction(email, Long.valueOf(postId), commentReaction);
    }

    /*@RequestMapping(value = "/add_temp_post", method = RequestMethod.GET)
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
    }*/
}
