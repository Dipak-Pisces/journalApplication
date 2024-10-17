package com.dipaklearn.journalApplication.Controller;

import com.dipaklearn.journalApplication.Entity.UserData;
import com.dipaklearn.journalApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @GetMapping("/poll")
    public String healthCheck(){
        return "Yes";
    }

    @GetMapping
    public ResponseEntity<List<UserData>> getUsers(){
        return new ResponseEntity(userService.getAll(),HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> addUser(@RequestBody UserData userData){
        try{
            userService.saveEntry(userData);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
