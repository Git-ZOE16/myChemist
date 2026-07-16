package ng.myChemist.data.repositories;

import ng.myChemist.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private List<User> Users = new ArrayList<>();

    @Override
    public void save(User User) {
        Users.add(User);

    }

    @Override
    public User findById(String id) {
        for(User User : Users){
            if(User.getId().equals(id)){
                return User;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return Users;
    }

    @Override
    public void delete(String id) {
        User User = findById(id);

        if(User != null) {
            Users.remove(User);
        }
    }

    @Override
    public int count() {
        return Users.size();
    }
}
