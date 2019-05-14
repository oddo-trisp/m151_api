package gr.di.uoa.m151.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LIKE")
public class LikeReaction extends UserPostReaction {
    public LikeReaction(){
        reactionType = "LIKE";
    }

    public LikeReaction(Post post, AppUser appUser){
        super(post, appUser);
        reactionType = "LIKE";
    }
}
