package gr.di.uoa.m151.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class AppUserCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    protected String email;

    @Basic
    @Column(name = "full_name", nullable = false, length = 128)
    protected String fullName;

    @Basic
    @Column(name = "user_image", length = -1)
    protected String userImage;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
