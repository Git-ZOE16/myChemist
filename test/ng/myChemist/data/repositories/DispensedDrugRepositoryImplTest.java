package ng.myChemist.data.repositories;

import ng.myChemist.data.models.DispensedDrug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DispensedDrugRepositoryImplTest {

    private  DispensedDrugRepositoryImpl repository ;

    @BeforeEach
        public void DispensedDrugRepositoryImpl(){
         repository = new DispensedDrugRepositoryImpl();
    }

    @Test
    public void TestAnEmptyRepository(){

        assertEquals(0, repository.count());
    }

    @Test
    public void TestToSaveOneDispensedDrugAndCountBecomesOne(){
        DispensedDrug dispensedDrug = new DispensedDrug();
        repository.save(dispensedDrug);
        assertEquals(1, repository.count());
    }
    @Test
    public void TestToSaveTwoDispensedDrugsAndCountBecomesTwo(){
        DispensedDrug dispensedDrug1 = new DispensedDrug();
        DispensedDrug dispensedDrug2 = new DispensedDrug();

        repository.save(dispensedDrug1);
        repository.save(dispensedDrug2);

        assertEquals(2, repository.count());
    }
    @Test
    public void TestToFindDispensedDrugByIdAndReturnCorrectDispensedDrug(){
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrug.setId("D001");
        repository.save(dispensedDrug);

        DispensedDrug foundDispensedDrug = repository.findById("D001");
        assertNotNull(foundDispensedDrug);
    }
    @Test
    public void TestToFindAllAndReturnEmptyWhenTheRepositoryIsEmpty(){

        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void TestThatFindAllMethodReturnsAllSavedDispensedDrugs(){
        repository.save(new DispensedDrug());
        repository.save(new DispensedDrug());
        repository.save(new DispensedDrug());
        repository.save(new DispensedDrug());

        assertEquals(4, repository.findAll().size());

    }
    @Test
    public void TestToDeleteDispensedDrugAndRepositoryBecomesEmpty(){
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrug.setId("D001");

        repository.save(dispensedDrug);

        repository.delete("D001");
        assertEquals(0, repository.count());
    }
    @Test
    public void TestToDeleteOneDispensedDrugAndCountReducesByOne(){
        DispensedDrug dispensedDrug1 = new DispensedDrug();
        dispensedDrug1.setId("D001");

        DispensedDrug dispensedDrug2 = new DispensedDrug();
        dispensedDrug2.setId("D002");

        repository.save(dispensedDrug1);
        repository.save(dispensedDrug2);

        repository.delete("D001");
        assertEquals(1, repository.count());
    }
    @Test
    public void TestThatDeletedDispensedDrugsCannotBeFoundAgain(){
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrug.setId("D001");

        repository.save(dispensedDrug);
        repository.delete("D001");

        assertNull(repository.findById("D001"));
    }
    @Test
    public void TestThatNonExistingDispensedDrugDoesNotChangeCount(){
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrug.setId("D001");

        repository.save(dispensedDrug);
        repository.delete("D222");
        assertEquals(1, repository.count());
    }
}
