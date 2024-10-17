package com.dipaklearn.journalApplication.Controller;

import com.dipaklearn.journalApplication.Entity.JournalData;
import com.dipaklearn.journalApplication.Entity.UserData;
import com.dipaklearn.journalApplication.Service.JournalEntryService;
import com.dipaklearn.journalApplication.Service.UserService;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<JournalData>> getJournalEntriesByUsername(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserData user = userService.findByUserName(username);
        if(user!=null && !user.getJournal_entries().isEmpty()){
            return new ResponseEntity(user.getJournal_entries(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalData> postJournalEntriesById(@PathVariable ObjectId myId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserData user = userService.findByUserName(username);

        List<JournalData> jData = user.getJournal_entries().stream().filter( x -> x.getId().equals(myId)).collect(Collectors.toList());

        if(!jData.isEmpty()){
            Optional<JournalData> byID = journalEntryService.findByID(myId);
            if(byID.isPresent()){
                return new ResponseEntity(byID,HttpStatus.OK);
            }
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<JournalData> postJournalEntriesByUsername(@RequestBody JournalData input){
        try{

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            journalEntryService.saveEntry(input,username);
            return new ResponseEntity(input, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }


    @PutMapping("id/{myId}")
    public ResponseEntity<JournalData> updateByID(
            @PathVariable ObjectId myId,
            @RequestBody JournalData input
    ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        JournalData old = journalEntryService.findByID(myId).orElse(null);
        if(old != null){
            old.setTitle( input.getTitle() !=null && !input.getTitle().equals("")? input.getTitle() : old.getTitle());
            old.setContent( input.getContent() !=null && !input.getContent().equals("")? input.getContent() : old.getContent());
            journalEntryService.updateEntry(old,username);
            return new ResponseEntity(old, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        boolean removed = journalEntryService.deleteByID(myId,username);
        if(removed)
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
