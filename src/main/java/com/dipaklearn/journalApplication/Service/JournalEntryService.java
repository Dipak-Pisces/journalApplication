package com.dipaklearn.journalApplication.Service;

import com.dipaklearn.journalApplication.Entity.JournalData;
import com.dipaklearn.journalApplication.Entity.UserData;
import com.dipaklearn.journalApplication.Repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalEntryService {

    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalData journalData, String username){
       try{
           UserData user = userService.findByUserName(username);

           journalData.setDate(LocalDateTime.now());
           JournalData saved = journalRepository.save(journalData);
           user.getJournal_entries().add(saved);
           userService.saveEntry(user);
       }catch (Exception e){
           System.out.println(e.getLocalizedMessage());
           throw new RuntimeException("User Not Found");
       }
    }

    public void updateEntry(JournalData journalData,String username){
        try{
            UserData user = userService.findByUserName(username);
            journalData.setDate(LocalDateTime.now());

            List<JournalData> l1 = user.getJournal_entries();
            l1 = l1.stream()
                    .filter(x -> !x.getId().equals(journalData.getId()))
                    .collect(Collectors.toList());
            l1.add(journalData);
            user.setJournal_entries(l1);

            journalRepository.save(journalData);
            userService.saveEntry(user);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            throw new RuntimeException("User Not Found");
        }
    }

    public List<JournalData> getAll(){
        return journalRepository.findAll();
    }

    public Optional<JournalData> findByID(ObjectId id){
        return journalRepository.findById(id);
    }

    public boolean deleteByID(ObjectId Id, String username){
        boolean removed=false;
        try{
            UserData user = userService.findByUserName(username);
            removed = user.getJournal_entries()
                    .removeIf(x -> x.getId().equals(Id));

            if(removed == true){
                journalRepository.deleteById(Id);
                userService.saveEntry(user);
            }
            return removed;
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return removed;
    }


}
