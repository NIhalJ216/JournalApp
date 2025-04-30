package com.edigest.javaBootProject.service;

import com.edigest.javaBootProject.entity.User;
import com.edigest.javaBootProject.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Disabled
    @Test
    public void testFindUserByName(){
//        assertEquals(4,2+2);
        assertNotNull(userRepository.findByUserName("johndoe"));
        User user = userRepository.findByUserName("johndoe");
        assertFalse(user.getJournalEntries().isEmpty());
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "johndoe",
            "smith",
            "katypery"
    })
    public void testParameterizedValueSource(String name){
        assertNotNull(userRepository.findByUserName(name));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected,a+b, "failed for: " + a + " & " + b);
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.createNewUser(user));
    }
}
