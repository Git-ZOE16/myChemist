package ng.myChemist.data.repositories;

import ng.myChemist.data.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryImplTest {

    @Test
    public void TestAnEmptyRepository(){
        UserRepository repository = new UserRepositoryImpl();
        assertEquals(0, repository.count());
    }
    @Test
    public void TestToSaveOneUserAndCountBecomesOne(){
        UserRepository repository = new UserRepositoryImpl();
        User User = new User();
        repository.save(User);
        assertEquals(1, repository.count());
    }
    @Test
    public void TestToSaveTwoUsersAndCountBecomesTwo(){
        UserRepository repository = new UserRepositoryImpl();

        User User1 = new User();
        User User2 = new User();

        repository.save(User1);
        repository.save(User2);

        assertEquals(2, repository.count());
    }
    @Test
    public void TestToFindUserByIdAndReturnCorrectUser(){
        UserRepository repository = new UserRepositoryImpl();
        User User = new User();
        User.setId("D001");
        repository.save(User);

        User foundUser = repository.findById("D001");
        assertNotNull(foundUser);
    }
    @Test
    public void TestToFindAllAndReturnEmptyWhenTheRepositoryIsEmpty(){
        UserRepository repository = new UserRepositoryImpl();

        assertEquals(0, repository.findAll().size());
    }
    @Test
    public void TestThatFindAllMethodReturnsAllSavedUsers(){
        UserRepository repository = new UserRepositoryImpl();

        repository.save(new User());
        repository.save(new User());
        repository.save(new User());
        repository.save(new User());

        assertEquals(4, repository.findAll().size());

    }
    @Test
    public void TestToDeleteUserAndRepositoryBecomesEmpty(){
        UserRepository repository = new UserRepositoryImpl();

        User User = new User();
        User.setId("D001");

        repository.save(User);

        repository.delete("D001");
        assertEquals(0, repository.count());
    }
    @Test
    public void TestToDeleteOneUserAndCountReducesByOne(){
        UserRepository repository = new UserRepositoryImpl();

        User User1 = new User();
        User1.setId("D001");

        User User2 = new User();
        User2.setId("D002");

        repository.save(User1);
        repository.save(User2);

        repository.delete("D001");
        assertEquals(1, repository.count());
    }
    @Test
    public void TestThatDeletedUsersCannotBeFoundAgain(){
        UserRepository repository = new UserRepositoryImpl();

        User User = new User();
        User.setId("D001");

        repository.save(User);
        repository.delete("D001");

        assertNull(repository.findById("D001"));
    }
    @Test
    public void TestThatNonExistingUserDoesNotChangeCount(){
        UserRepository repository = new UserRepositoryImpl();

        User User = new User();
        User.setId("D001");

        repository.save(User);
        repository.delete("D222");
        assertEquals(1, repository.count());
    }
}
