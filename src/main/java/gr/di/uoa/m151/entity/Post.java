package gr.di.uoa.m151.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post", schema = "public")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "post_title", length = -1)
    private String postTitle;

    @Basic
    @Column(name = "post_text", length = -1)
    private String postText;

    @Basic
    @Column(name = "creation_date")
    private Timestamp creationDate;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    @JsonBackReference
    private AppUser appUser;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    //@JsonManagedReference
    private List<UserPostReaction> userReactions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public List<UserPostReaction> getUserReactions() {
        return userReactions;
    }

    public void setUserReactions(List<UserPostReaction> userReactions) {
        this.userReactions = userReactions;
    }

    public void addUserPostReaction(UserPostReaction userPostReaction) {
        userPostReaction.setPost(this);
        userReactions.add(userPostReaction);
    }

    public void removeUserReaction(AppUser appUser) {
        userReactions.stream()
                .filter(ur -> this.equals(ur.getPost()))
                .filter(ur -> appUser.equals(ur.getAppUser()))
                .forEach(ur ->{
                    userReactions.remove(ur);
                    //ur.getAppUser().removePostReaction(ur);
                    ur.setPost(null);
                    ur.setAppUser(null);
                });
        //userReactions.forEach(ur -> {
        //    if(ur.getPost().equals(this) && ur.getAppUser().equals(appUser)){
        //        userReactions.remove(ur);
        //        ur.getAppUser().getPostReactions().remove(ur);
        //        ur.setPost(null);
        //        ur.setAppUser(null);
        //    }
        //});
    }
}
