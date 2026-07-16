package ng.myChemist.data.repositories;

import ng.myChemist.data.models.User;

import java.util.List;

public interface UserRepository {

    void save(User User);
    User findById(String id);
    List<User> findAll();
    void delete(String id);
    int count();

}