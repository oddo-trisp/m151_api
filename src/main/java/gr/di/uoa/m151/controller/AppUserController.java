package gr.di.uoa.m151.controller;

import gr.di.uoa.m151.entity.AppUser;
import gr.di.uoa.m151.entity.AppUserShort;
import gr.di.uoa.m151.entity.Post;
import gr.di.uoa.m151.service.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class AppUserController {

    final private AppUserServiceImpl appUserService;

    @Autowired
    public AppUserController(AppUserServiceImpl appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(value = "/findAllAppUsers", method = RequestMethod.GET)
    public Iterable<AppUser> findAllAppUsers() {
        return appUserService.findAllAppUsers();
    }

    @RequestMapping(value = "/parser", method = RequestMethod.GET)
    public Iterable<AppUser> persistUsers() throws IOException {
        return appUserService.parseUsers();
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser signUp(@RequestBody AppUser newAppUser) {
        return appUserService.saveAppUser(newAppUser);
    }

    @RequestMapping(value = "/findAppUserByEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser findAppUserByEmail(@RequestParam String email) {
        return appUserService.findAppUserByEmail(email);
    }

    @RequestMapping(value = "/addNewPost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser addNewPost(@RequestParam String email,@RequestBody Post newPost) {
        return appUserService.addNewPost(email,newPost);
    }

    @RequestMapping(value = "/findSuggestions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AppUserShort> findSuggestions(@RequestParam String email) {
        return appUserService.findSuggestions(email);
    }

    @RequestMapping(value = "/followUser", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser followUser(@RequestParam String email, @RequestParam String userId) {
        return appUserService.followUser(email, Long.valueOf(userId));
    }
}
