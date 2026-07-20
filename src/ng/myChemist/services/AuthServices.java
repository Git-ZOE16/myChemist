package ng.myChemist.services;

import ng.myChemist.data.models.User;
import ng.myChemist.data.repositories.UserRepository;
import ng.myChemist.dto.request.LoginRequest;
import ng.myChemist.dto.response.LoginResponse;

public class AuthServices {


    private UserRepository userRepository;

    public AuthServices(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null){
            throw new RuntimeException("User not found");
        }
        if(!user.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        LoginResponse response = new LoginResponse();
        response.setId(user.getId());
        response.setMessage("Login Successful");
        return response;
    }
}
