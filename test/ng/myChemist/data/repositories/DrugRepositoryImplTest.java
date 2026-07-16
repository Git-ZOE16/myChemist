package ng.myChemist.data.repositories;

import ng.myChemist.data.models.Drug;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DrugRepositoryImplTest {

    @Test
    public void TestAnEmptyRepository(){
        DrugRepository repository = new DrugRepositoryImpl();
        assertEquals(0, repository.count());
    }
    @Test
    public void TestToSaveOneDrugAndCountBecomesOne(){
        DrugRepository repository = new DrugRepositoryImpl();
        Drug drug = new Drug();
        repository.save(drug);
        assertEquals(1, repository.count());
    }
    @Test
    public void TestToSaveTwoDrugsAndCountBecomesTwo(){
        DrugRepository repository = new DrugRepositoryImpl();

        Drug drug1 = new Drug();
        Drug drug2 = new Drug();

        repository.save(drug1);
        repository.save(drug2);

        assertEquals(2, repository.count());
    }
    @Test
    public void TestToFindDrugByIdAndReturnCorrectDrug(){
        DrugRepository repository = new DrugRepositoryImpl();
        Drug drug = new Drug();
        drug.setId("D001");
        repository.save(drug);

        Drug foundDrug = repository.findById("D001");
        assertNotNull(foundDrug);
    }
    @Test
    public void TestToFindAllAndReturnEmptyWhenTheRepositoryIsEmpty(){
        DrugRepository repository = new DrugRepositoryImpl();

        assertEquals(0, repository.findAll().size());
    }
    @Test
    public void TestThatFindAllMethodReturnsAllSavedDrugs(){
        DrugRepository repository = new DrugRepositoryImpl();

        repository.save(new Drug());
        repository.save(new Drug());
        repository.save(new Drug());
        repository.save(new Drug());

        assertEquals(4, repository.findAll().size());

    }
    @Test
    public void TestToDeleteDrugAndRepositoryBecomesEmpty(){
        DrugRepository repository = new DrugRepositoryImpl();

        Drug drug = new Drug();
        drug.setId("D001");

        repository.save(drug);

        repository.delete("D001");
        assertEquals(0, repository.count());
    }
    @Test
    public void TestToDeleteOneDrugAndCountReducesByOne(){
        DrugRepository repository = new DrugRepositoryImpl();

        Drug drug1 = new Drug();
        drug1.setId("D001");

        Drug drug2 = new Drug();
        drug2.setId("D002");

        repository.save(drug1);
        repository.save(drug2);

        repository.delete("D001");
        assertEquals(1, repository.count());
    }
    @Test
    public void TestThatDeletedDrugsCannotBeFoundAgain(){
        DrugRepository repository = new DrugRepositoryImpl();

        Drug drug = new Drug();
        drug.setId("D001");

        repository.save(drug);
        repository.delete("D001");

        assertNull(repository.findById("D001"));
    }
    @Test
    public void TestThatNonExistingDrugDoesNotChangeCount(){
        DrugRepository repository = new DrugRepositoryImpl();

        Drug drug = new Drug();
        drug.setId("D001");

        repository.save(drug);
        repository.delete("D222");
        assertEquals(1, repository.count());
    }
}
