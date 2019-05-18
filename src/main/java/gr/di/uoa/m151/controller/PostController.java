package gr.di.uoa.m151.controller;

import gr.di.uoa.m151.entity.CommentReaction;
import gr.di.uoa.m151.entity.LikeReaction;
import gr.di.uoa.m151.entity.Post;
import gr.di.uoa.m151.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/addLikeReaction", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Post addLikeReaction(@RequestParam String email, @RequestParam String postId) {
        LikeReaction likeReaction = new LikeReaction();
        return postService.addUserPostReaction(email, Long.valueOf(postId), likeReaction);
    }

    @RequestMapping(value = "/removeLikeReaction", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Post removeLikeReaction(@RequestParam String email, @RequestParam String postId, @RequestParam String likeId) {
        return postService.removeUserPostReaction(email, Long.valueOf(postId), Long.valueOf(likeId));
    }

    @RequestMapping(value = "/findRecentPosts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Post> findRecentPosts(@RequestParam String email) {
        return postService.findRecentPosts(email);
    }
}
