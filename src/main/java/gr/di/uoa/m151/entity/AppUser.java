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
public class AppUser extends AppUserCore implements Serializable {

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    private String email;

    @Basic
    @Column(name = "password", nullable = false, length = 128)
    private String password;

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
    private Set<AppUserShort> followersShort = new HashSet<>();

    @Transient
    private Set<AppUserShort> followingsShort = new HashSet<>();




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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

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

    public Set<AppUserShort> getFollowersShort() {
        return followersShort;
    }

    public void setFollowersShort(Set<AppUserShort> followersShort) {
        this.followersShort = followersShort;
    }

    public Set<AppUserShort> getFollowingsShort() {
        return followingsShort;
    }

    public void setFollowingsShort(Set<AppUserShort> followingsShort) {
        this.followingsShort = followingsShort;
    }

    @PostLoad
    public void init() {
        followersShort = followers.stream().map(AppUserShort::new).collect(Collectors.toSet());
        followingsShort = followings.stream().map(AppUserShort::new).collect(Collectors.toSet());
    }
}
