package com.dipaklearn.journalApplication.Controller;

import com.dipaklearn.journalApplication.APIResponse.WeatherResponse;
import com.dipaklearn.journalApplication.Entity.UserData;
import com.dipaklearn.journalApplication.Service.UserService;
import com.dipaklearn.journalApplication.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserData userData){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserData user = userService.findByUserName(username);
        if( user !=null){
            user.setUsername(userData.getUsername());
            user.setPassword(userData.getPassword());
            userService.saveEntry(user);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    protected void deleteByUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.deleteByUserName(username);
    }

    @GetMapping
    protected ResponseEntity<?> getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        WeatherResponse response = weatherService.getWeather("Indore");
        String greeting ="";
        if(response !=null){
            greeting = " weather feels like "+ response.getCurrent().getFeelslike();
        }
        return new ResponseEntity("Hi "+username+ greeting,HttpStatus.OK);
    }

}
