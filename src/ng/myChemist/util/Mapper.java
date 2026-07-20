package ng.myChemist.util;

import ng.myChemist.data.models.User;
import ng.myChemist.dto.request.RegisterUserRequest;
import ng.myChemist.dto.response.RegisterUserResponse;

public class Mapper {

    public static User map(RegisterUserRequest request){
        User user = new User();

        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setPassword(request.getPassword());

        return user;
    }
    public static RegisterUserResponse map(User user){
        RegisterUserResponse response = new RegisterUserResponse();

        response.setId(user.getId());
        response.setMessage("User registered Successfully");

        return response;

    }
}
