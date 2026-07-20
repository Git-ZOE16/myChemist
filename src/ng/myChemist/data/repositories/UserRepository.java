package ng.myChemist.data.repositories;

import ng.myChemist.data.models.User;

import java.util.List;

public interface UserRepository {

    void save(User user);
    User findById(int id);
    List<User> findAll();
    void delete(int id);
    int count();
    User findByUsername(String username);

}