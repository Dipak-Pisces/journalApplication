package com.dipaklearn.journalApplication.Service;

import com.dipaklearn.journalApplication.Entity.JournalData;
import com.dipaklearn.journalApplication.Entity.UserData;
import com.dipaklearn.journalApplication.Repository.JournalRepository;
import com.dipaklearn.journalApplication.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.LogFactory;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //private static final Logger logger =  LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public boolean saveAdmin(UserData userData){
        try{
            if(userData.getPassword().length() <15)
                userData.setPassword(passwordEncoder.encode(userData.getPassword()));

            userData.setRoles(Arrays.asList("USER","ADMIN"));
            userRepository.save(userData);
            return true;
        }catch (Exception e){
            log.error("problem with user {} -- ",userData.getUsername(),e);
            return false;
        }

    }

    public boolean saveEntry(UserData userData){
        try{
            if(userData.getPassword().length() <15)
                userData.setPassword(passwordEncoder.encode(userData.getPassword()));
            userData.setRoles(Arrays.asList("USER"));
            userRepository.save(userData);
            return true;
        }catch (Exception e){
            log.trace("problem with user {} -- ",userData.getUsername());
            log.debug("problem with user {} -- ",userData.getUsername());
            log.info("problem with user {} -- ",userData.getUsername());
            log.warn("problem with user {} -- ",userData.getUsername());
            log.error("problem with user {} -- ",userData.getUsername());
            return false;
        }
    }

    public List<UserData> getAll(){
        return userRepository.findAll();
    }

    public Optional<UserData> findByID(ObjectId id){
        return userRepository.findById(id);
    }

    public boolean deleteByID(ObjectId Id){
        userRepository.deleteById(Id);
        return true;
    }

    public UserData findByUserName(String username){
        return userRepository.findUserByUsername(username);
    }

    public void deleteByUserName(String username){
        userRepository.deleteByUsername(username);
    }




}
