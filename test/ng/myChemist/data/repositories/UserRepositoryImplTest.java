package ng.myChemist.data.repositories;

import ng.myChemist.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryImplTest {

    private  UserRepositoryImpl repository ;

    @BeforeEach
    public void UserRepositoryImpl(){
        repository = new UserRepositoryImpl();
    }

    @Test
    public void TestAnEmptyRepository(){
        assertEquals(0, repository.count());
    }
    @Test
    public void TestToSaveOneUserAndCountBecomesOne(){
        User user = new User();
        repository.save(user);
        assertEquals(1, repository.count());
    }
    @Test
    public void TestToSaveTwoUsersAndCountBecomesTwo(){
        User user1 = new User();
        User user2 = new User();

        repository.save(user1);
        repository.save(user2);

        assertEquals(2, repository.count());
    }
    @Test
    public void TestToFindUserByIdAndReturnCorrectUser(){
        User user = new User();
        user.setId(1);
        repository.save(user);

        User foundUser = repository.findById(1);
        assertNotNull(foundUser);
    }

    @Test
    public void TestToFindUserByUsername_userExists_returnCorrectUser(){
        User user = new User();
        user.setId(1);
        user.setUsername("User");

        repository.save(user);

        User foundUser = repository.findByUsername("User");
        assertNotNull(foundUser);
    }

    @Test
    public void TestToFindAllAndReturnEmptyWhenTheRepositoryIsEmpty(){

        assertEquals(0, repository.findAll().size());
    }
    @Test
    public void TestThatFindAllMethodReturnsAllSavedUsers(){
        repository.save(new User());
        repository.save(new User());
        repository.save(new User());
        repository.save(new User());

        assertEquals(4, repository.findAll().size());

    }
    @Test
    public void TestToDeleteUserAndRepositoryBecomesEmpty(){
        User user = new User();
        user.setId(1);

        repository.save(user);

        repository.delete(1);
        assertEquals(0, repository.count());
    }
    @Test
    public void TestToDeleteOneUserAndCountReducesByOne(){
        User user1 = new User();
        user1.setId(1);

        User user2 = new User();
        user2.setId(2);

        repository.save(user1);
        repository.save(user2);

        repository.delete(1);
        assertEquals(1, repository.count());
    }
    @Test
    public void TestThatDeletedUsersCannotBeFoundAgain(){
        User user = new User();
        user.setId(1);

        repository.save(user);
        repository.delete(1);

        assertNull(repository.findById(1));
    }
    @Test
    public void TestThatNonExistingUserDoesNotChangeCount(){
        User user = new User();
        user.setId(1);

        repository.save(user);
        repository.delete(2);
        assertEquals(1, repository.count());
    }
}
