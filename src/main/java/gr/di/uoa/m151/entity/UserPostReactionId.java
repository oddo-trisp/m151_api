package gr.di.uoa.m151.entity;

import javax.persistence.Column;
import java.io.Serializable;

public class UserPostReactionId implements Serializable {

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "app_user_id")
    private Long appUserId;

    private UserPostReactionId() {
    }

    public UserPostReactionId(Long postId, Long appUserId) {
        this.postId = postId;
        this.appUserId = appUserId;
    }
}
