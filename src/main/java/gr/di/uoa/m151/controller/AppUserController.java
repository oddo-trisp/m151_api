package gr.di.uoa.m151.controller;

import gr.di.uoa.m151.entity.AppUser;
import gr.di.uoa.m151.service.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
}
