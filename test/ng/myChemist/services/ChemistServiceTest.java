package ng.myChemist.services;

import ng.myChemist.dto.request.RegisterUserRequest;
import ng.myChemist.dto.response.RegisterUserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChemistServiceTest {

    @Test
    public void testToRegisterUserAndReturnResponse(){
        ChemistService service = new ChemistService();
        RegisterUserRequest request = new RegisterUserRequest();

        request.setUsername("Oluyemi");
        request.setFullName("Oluyemi Zoe");
        request.setPassword("150122");

        RegisterUserResponse response = service.registerUser(request);
        assertNotNull(response);
    }
}
