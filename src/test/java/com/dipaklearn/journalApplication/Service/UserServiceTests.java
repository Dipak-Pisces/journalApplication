package com.dipaklearn.journalApplication.Service;

import com.dipaklearn.journalApplication.Entity.UserData;
import com.dipaklearn.journalApplication.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(UserData userData){
        assertTrue(userService.saveEntry(userData));
    }

    @BeforeEach
    public void run(){
        MockitoAnnotations.initMocks(this);
    }


    @Disabled
    @ParameterizedTest
    @CsvSource({
            "ajay",
            "kriti",
            "dheeraj"
    })
    public void testFindByUserName(String username){
        assertNotNull(userRepository.findUserByUsername(username));
    }
}
