package ng.myChemist.data.repositories;

import ng.myChemist.data.models.DispensedDrug;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DispensedDrugRepositoryImplTest {

    @Test
    public void TestAnEmptyRepository(){
        DispensedDrugRepository repository = new DispensedDrugRepositoryImpl();
        assertEquals(0, repository.count());
    }
    @Test
    public void TestToSaveOneDispensedDrugAndCountBecomesOne(){
        DispensedDrugRepository repository = new DispensedDrugRepositoryImpl();
        DispensedDrug DispensedDrug = new DispensedDrug();
        repository.save(DispensedDrug);
        assertEquals(1, repository.count());
    }
    @Test
    public void TestToSaveTwoDispensedDrugsAndCountBecomesTwo(){
        DispensedDrugRepository repository = new DispensedDrugRepositoryImpl();

        DispensedDrug DispensedDrug1 = new DispensedDrug();
        DispensedDrug DispensedDrug2 = new DispensedDrug();

        repository.save(DispensedDrug1);
        repository.save(DispensedDrug2);

        assertEquals(2, repository.count());
    }
    @Test
    public void TestToFindDispensedDrugByIdAndReturnCorrectDispensedDrug(){
        DispensedDrugRepository repository = new DispensedDrugRepositoryImpl();
        DispensedDrug DispensedDrug = new DispensedDrug();
        DispensedDrug.setId("D001");
        repository.save(DispensedDrug);

        DispensedDrug foundDispensedDrug = repository.findById("D001");
        assertNotNull(foundDispensedDrug);
    }
    @Test
    public void TestToFindAllAndReturnEmptyWhenTheRepositoryIsEmpty(){
        DispensedDrugRepository repository = new DispensedDrugRepositoryImpl();

        assertEquals(0, repository.findAll().size());
    }
    @Test
    public void TestThatFindAllMethodReturnsAllSavedDispensedDrugs(){
        DispensedDrugRepository repository = new DispensedDrugRepositoryImpl();

        repository.save(new DispensedDrug());
        repository.save(new DispensedDrug());
        repository.save(new DispensedDrug());
        repository.save(new DispensedDrug());

        assertEquals(4, repository.findAll().size());

    }
    @Test
    public void TestToDeleteDispensedDrugAndRepositoryBecomesEmpty(){
        DispensedDrugRepository repository = new DispensedDrugRepositoryImpl();

        DispensedDrug DispensedDrug = new DispensedDrug();
        DispensedDrug.setId("D001");

        repository.save(DispensedDrug);

        repository.delete("D001");
        assertEquals(0, repository.count());
    }
    @Test
    public void TestToDeleteOneDispensedDrugAndCountReducesByOne(){
        DispensedDrugRepository repository = new DispensedDrugRepositoryImpl();

        DispensedDrug DispensedDrug1 = new DispensedDrug();
        DispensedDrug1.setId("D001");

        DispensedDrug DispensedDrug2 = new DispensedDrug();
        DispensedDrug2.setId("D002");

        repository.save(DispensedDrug1);
        repository.save(DispensedDrug2);

        repository.delete("D001");
        assertEquals(1, repository.count());
    }
    @Test
    public void TestThatDeletedDispensedDrugsCannotBeFoundAgain(){
        DispensedDrugRepository repository = new DispensedDrugRepositoryImpl();

        DispensedDrug DispensedDrug = new DispensedDrug();
        DispensedDrug.setId("D001");

        repository.save(DispensedDrug);
        repository.delete("D001");

        assertNull(repository.findById("D001"));
    }
    @Test
    public void TestThatNonExistingDispensedDrugDoesNotChangeCount(){
        DispensedDrugRepository repository = new DispensedDrugRepositoryImpl();

        DispensedDrug DispensedDrug = new DispensedDrug();
        DispensedDrug.setId("D001");

        repository.save(DispensedDrug);
        repository.delete("D222");
        assertEquals(1, repository.count());
    }
}
