package ng.myChemist.services;

import ng.myChemist.data.models.User;
import ng.myChemist.data.repositories.UserRepository;
import ng.myChemist.data.repositories.UserRepositoryImpl;
import ng.myChemist.dto.request.RegisterUserRequest;
import ng.myChemist.dto.response.RegisterUserResponse;
import ng.myChemist.util.Mapper;

public class ChemistService {

    private UserRepository userRepository = new UserRepositoryImpl();

    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        User user = Mapper.map(request);
        userRepository.save(user);
        return Mapper.map(user);
    }
}