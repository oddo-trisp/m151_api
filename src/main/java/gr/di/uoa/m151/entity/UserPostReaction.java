package gr.di.uoa.m151.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_post_reaction", schema = "public")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "reaction_type", discriminatorType = DiscriminatorType.STRING)
public abstract class UserPostReaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    @JsonIgnore
    protected AppUser appUser;

    @Transient
    protected String reactionType;

    @Transient
    protected AppUserShort appUserShort;

    public UserPostReaction() {
    }

    public UserPostReaction(Post post, AppUser appUser) {
        this.post = post;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }

    public AppUserShort getAppUserShort() {
        return appUserShort;
    }

    public void setAppUserShort(AppUserShort appUserShort) {
        this.appUserShort = appUserShort;
    }

    @PostLoad
    public void init(){
        if(appUser != null)
            appUserShort = new AppUserShort(appUser);
    }
}
