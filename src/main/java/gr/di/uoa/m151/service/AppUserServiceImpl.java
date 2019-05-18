package gr.di.uoa.m151.service;

import gr.di.uoa.m151.entity.AppUser;
import gr.di.uoa.m151.entity.AppUserShort;
import gr.di.uoa.m151.entity.Post;
import gr.di.uoa.m151.repo.AppUserShortRepository;
import gr.di.uoa.m151.repo.AppUserRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl {

    final private static String APP_USERS = "data/citizen_file.csv";

    final private AppUserRepository appUserRepository;
    final private AppUserShortRepository appUserShortRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserShortRepository appUserShortRepository) {
        this.appUserRepository = appUserRepository;
        this.appUserShortRepository = appUserShortRepository;
    }

    public Iterable<AppUser> findAllAppUsers(){
        return appUserRepository.findAll();
    }

    public Iterable<AppUser> parseUsers() throws IOException {
        Reader reader;
        reader = Files.newBufferedReader(Paths.get(APP_USERS));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());

        List<AppUser> appUsers = new ArrayList<>();
        //Long id = 1L;
        for (CSVRecord csvRecord : csvParser) {
            AppUser appUser = new AppUser();
            appUser.setFullName(csvRecord.get("full_name"));
            appUser.setEmail(csvRecord.get("email"));
            appUser.setPassword(csvRecord.get("password"));
            appUser.setEnabled(true);
            //appUser.setId(id);
            //id++;

            appUsers.add(appUser);
        }
        appUsers = appUsers
                .stream()
                .filter(distinctByKey(AppUser::getEmail))
                .collect(Collectors.toList());
        return appUserRepository.saveAll(appUsers.subList(0,4));

    }

    public AppUser saveAppUser(AppUser newAppUser) { return appUserRepository.save(newAppUser); }

    AppUser findUser(Long id){
        return appUserRepository.findById(id).orElse(null);
    }

    public AppUser findAppUserByEmail(String email) { return appUserRepository.findAppUserByEmail(email).orElse(null); }

    public AppUser findAppUserById(Long id) { return appUserRepository.findById(id).orElse(null); }

    public AppUser addNewPost(String email, Post post) {
        AppUser appUser = appUserRepository.findAppUserByEmail(email).orElse(null);

        if(appUser == null)
            return null;

        post.setCreationDate(new Timestamp(new Date().getTime()));
        appUser.addPost(post);
        return appUserRepository.save(appUser);
    }

    private static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> ke) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(ke.apply(t), Boolean.TRUE) == null;
    }

    public List<AppUserShort>  findSuggestions(String email){
        return appUserShortRepository.findSuggestions(email);
    }

    public List<AppUser>  findSuggestionsWithPosts(String email){
        return appUserRepository.findSuggestions(email);
    }

    public AppUser  followUser(String email, Long userId){
        AppUser mainUser = appUserRepository.findAppUserByEmail(email).orElse(null);
        AppUser userToFollow = appUserRepository.findById(userId).orElse(null);

        if(mainUser == null || userToFollow == null)
            return null;

        mainUser.addFollowing(userToFollow);
        AppUser persistedUser = appUserRepository.save(mainUser);
        persistedUser.addFollowingShort(userToFollow);
        return persistedUser;
    }

    public AppUser unfollowUser(String email, Long userId){
        AppUser mainUser = appUserRepository.findAppUserByEmail(email).orElse(null);
        AppUser userToUnfollow = appUserRepository.findById(userId).orElse(null);

        if(mainUser == null || userToUnfollow == null)
            return null;

        mainUser.removeFollowing(userToUnfollow);
        AppUser persistedUser = appUserRepository.save(mainUser);
        persistedUser.removeFollowingShort(userToUnfollow);
        return persistedUser;
    }
}
