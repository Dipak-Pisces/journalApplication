package com.dipaklearn.journalApplication.Repository;

import com.dipaklearn.journalApplication.Entity.UserData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void test(){
        List<UserData> records = userRepository.getUserForSA();
    }
}