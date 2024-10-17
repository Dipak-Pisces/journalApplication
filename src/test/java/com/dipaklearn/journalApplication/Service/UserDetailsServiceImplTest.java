package com.dipaklearn.journalApplication.Service;

import com.dipaklearn.journalApplication.Entity.UserData;
import com.dipaklearn.journalApplication.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void run(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void loadUserByUsername(){
        when(userRepository.findUserByUsername(ArgumentMatchers.anyString())).thenReturn( UserData.builder().username("dipa").password("dipak").roles(new ArrayList<>()).build());

        UserDetails user = userDetailsService.loadUserByUsername("dipak");
        assertNotNull(user);

    }

}
