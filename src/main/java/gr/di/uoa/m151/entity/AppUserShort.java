package gr.di.uoa.m151.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "app_user", schema = "public")
public class AppUserShort extends AppUserCore {

    public AppUserShort(){

    }

    public AppUserShort(AppUser appUser){
        this.id = appUser.id;
        this.fullName = appUser.fullName;
        this.userImage = appUser.userImage;
        this.email = appUser.email;
    }
}
