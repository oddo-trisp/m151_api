package gr.di.uoa.m151.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "app_user", schema = "public")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "full_name", nullable = false, length = 128)
    private String fullName;

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    private String email;

    @Basic
    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Basic
    @Column(name = "user_image", length = -1)
    private String userImage;

    @Basic
    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @Basic
    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @OneToMany(
            mappedBy = "appUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("creationDate DESC")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "follow",
            joinColumns = @JoinColumn(name = "main_user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "following_user_id", nullable = false)
    )
    @JsonIgnore
    private Set<AppUser> followings = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "follow",
            joinColumns = @JoinColumn(name = "following_user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "main_user_id", nullable = false)
    )
    @JsonIgnore
    private Set<AppUser> followers = new HashSet<>();

    @Transient
    private Set<AppUserData> followersData = new HashSet<>();

    @Transient
    private Set<AppUserData> followingsData = new HashSet<>();

    //TODO refactor to Map
    /*@OneToMany(
            mappedBy = "appUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserPostReaction> postReactions = new ArrayList<>();*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /*public List<UserPostReaction> getPostReactions() {
        return postReactions;
    }

    public void setPostReactions(List<UserPostReaction> postReactions) {
        this.postReactions = postReactions;
    }

    public void removePostReaction(UserPostReaction userPostReaction){
        postReactions.remove(userPostReaction);
    }*/

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        post.setAppUser(this);
        posts.add(post);
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Set<AppUser> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<AppUser> followings) {
        this.followings = followings;
    }

    public void addFollowing(AppUser following) {
        followings.add(following);
    }

    public void removeFollowing(AppUser following) {
        followings.remove(following);
    }

    public Set<AppUser> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<AppUser> followers) {
        this.followers = followers;
    }

    public void addFollower(AppUser follower) {
        followings.add(follower);
    }

    public void removeFollower(AppUser follower) {
        followings.remove(follower);
    }

    public Set<AppUserData> getFollowersData() {
        return followersData;
    }

    public void setFollowersData(Set<AppUserData> followersData) {
        this.followersData = followersData;
    }

    public Set<AppUserData> getFollowingsData() {
        return followingsData;
    }

    public void setFollowingsData(Set<AppUserData> followingsData) {
        this.followingsData = followingsData;
    }

    private AppUserData createAppUserData(AppUser appUser) {
        AppUserData appUserData = new AppUserData();
        appUserData.setId(appUser.id);
        appUserData.setFullName(appUser.fullName);
        appUserData.setUserImage(appUser.userImage);

        return appUserData;
    }

    @PostLoad
    public void init() {
        followersData = followers.stream().map(this::createAppUserData).collect(Collectors.toSet());
        followingsData = followings.stream().map(this::createAppUserData).collect(Collectors.toSet());
    }
}
