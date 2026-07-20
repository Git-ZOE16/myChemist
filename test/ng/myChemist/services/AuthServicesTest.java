package ng.myChemist.services;

import ng.myChemist.data.models.User;
import ng.myChemist.data.repositories.UserRepository;
import ng.myChemist.data.repositories.UserRepositoryImpl;
import ng.myChemist.dto.request.LoginRequest;
import ng.myChemist.dto.response.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServicesTest {
    private AuthServices authServices;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        userRepository = new UserRepositoryImpl();
        authServices = new AuthServices(userRepository);
    }

    @Test
    public void TestToLoginUser_UserExists_returnSuccessfulResponse(){
        User user = new User();
        user.setId(1);
        user.setUsername("Oluyemi");
        user.setPassword("150122");
        userRepository.save(user);

        LoginRequest request = new LoginRequest();
        request.setUsername("Oluyemi");
        request.setPassword("150122");

        LoginResponse response = authServices.login(request);

        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals("Login Successful", response.getMessage());
    }

    @Test
    public void TestToLoginUser_WithWrongPassword_throwsException(){
        User user = new User();
        user.setUsername("Oluyemi");
        user.setPassword("150122");

        userRepository.save(user);

        LoginRequest request = new LoginRequest();
        request.setUsername("Oluyemi");
        request.setPassword("1234");

       RuntimeException exception = assertThrows(RuntimeException.class, ()-> authServices.login(request));
        assertEquals("Invalid password", exception.getMessage());
    }

    @Test
    public void TestLoginUser_userDoesNotExist_throwsException(){
        LoginRequest request = new LoginRequest();
        request.setUsername("oluyemi");
        request.setPassword("150122");

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> authServices.login(request));
        assertEquals("User not found", exception.getMessage());

    }

    @Test
    public void TestLoginUser_withEmptyUserName_throwsException(){
        LoginRequest request = new LoginRequest();
        request.setUsername("");
        request.setPassword("150122");

        IllegalArgumentException assertThrows = IllegalArgumentException
    }
}
