package com.dipaklearn.journalApplication.Controller;

import com.dipaklearn.journalApplication.Entity.UserData;
import com.dipaklearn.journalApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    private ResponseEntity<?> getAllUsers(){
        List<UserData> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity(all, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-admin-user")
    private void createAdmin(@RequestBody UserData userData){
        userService.saveAdmin(userData);
    }
}
