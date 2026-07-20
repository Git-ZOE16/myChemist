package ng.myChemist.data.repositories;

import ng.myChemist.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);

    }

    @Override
    public User findById(int id) {
        for(User user : users){
            if(user.getId()== id){
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByUsername(String username){
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void delete(int id) {
        User user = findById(id);

        if(user != null) {
            users.remove(user);
        }
    }

    @Override
    public int count() {
        return users.size();
    }
}
