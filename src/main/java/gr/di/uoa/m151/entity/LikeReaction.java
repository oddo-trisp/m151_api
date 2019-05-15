package gr.di.uoa.m151.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PostLoad;

@Entity
@DiscriminatorValue("LIKE")
public class LikeReaction extends UserPostReaction {
    public LikeReaction(){
    }

    public LikeReaction(Post post, AppUser appUser){
        super(post, appUser);
    }

    @PostLoad
    @Override
    public void init(){
        reactionType = "LIKE";
        super.init();
    }

}
