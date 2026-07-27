package ng.myChemist.data.repositories;

import ng.myChemist.data.models.Batch;
import ng.myChemist.data.models.Drug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BatchRepositoryImplTest {
    private BatchRepositoryImpl repository;

    @BeforeEach
    public void setUp(){
        repository = new BatchRepositoryImpl();
    }

    @Test
    public void TestAnEmptyRepository_RepositoryIsEmpty(){
        assertEquals(0, repository.count());
    }

    @Test
    public void TestToSaveOneBatch_BatchCountIsOne(){
        Batch batch = new Batch();

        repository.save(batch);

        assertEquals(1, repository.count());
    }

    @Test
    public void TestToSaveTwoBatches_BatchCountIsTwo(){
        Batch batch1 = new Batch();
        Batch batch2 = new Batch();

        repository.save(batch1);
        repository.save(batch2);

        assertEquals(2, repository.count());
    }

    @Test
    public void TestToFindBatch_ById(){
        Batch batch = new Batch();
        batch.setId("B001");

        repository.save(batch);

        Batch foundBatch = repository.findById("B001");

        assertNotNull(foundBatch);
    }

    @Test
    public void TestToFindBatch_ById_ReturnCorrectBatch(){
        Batch batch = new Batch();
        batch.setId("B001");

        repository.save(batch);

        Batch foundBatch = repository.findById("B001");

        assertNotNull(foundBatch);
        assertEquals("B001", foundBatch.getId());
    }

    @Test
    public void TestToFindABatch_ReturnTheDrugInside(){
        Drug drug = new Drug();
        drug.setName("Paracetamol");

        Batch batch = new Batch();
        batch.setId("B001");
        batch.setDrug(drug);

        repository.save(batch);

        Batch foundBatch = repository.findById("B001");

        assertNotNull(foundBatch);
        assertEquals("Paracetamol", foundBatch.getDrug().getName());
    }

    @Test
    public void TestToFindBatches_ReturnAllBatches(){
        Batch batch1 = new Batch();
        Batch batch2 = new Batch();
        Batch batch3 = new Batch();

        repository.save(batch1);
        repository.save(batch2);
        repository.save(batch3);

        assertEquals(3, repository.findAll().size());
    }

    @Test
    public void TestToDeleteOneBatch_BatchIsDeleted_BatchCountReducesByOne(){
        Batch batch1 = new Batch();
        batch1.setId("B001");

        Batch batch2 = new Batch();
        batch2.setId("B002");

        Batch batch3 = new Batch();
        batch3.setId("B003");

        repository.save(batch1);
        repository.save(batch2);
        repository.save(batch3);

        repository.delete("B001");

        assertEquals(2, repository.count());
        assertNotNull(repository.findById("B002"));
        assertNotNull(repository.findById("B003"));
        assertNull(repository.findById("B001"));
    }

    @Test
    public void TestToDelete_AllBatches_RepositoryIsEmpty(){
        Batch batch1 = new Batch();
        Batch batch2 = new Batch();
        Batch batch3 = new Batch();

        repository.save(batch1);
        repository.save(batch2);
        repository.save(batch3);

        repository.deleteAll();

        assertEquals(0, repository.count());
    }

    @Test
        public void TestThatBatchIsDeleted_TheBatchCannotBeFound(){
            Batch batch = new Batch();
            batch.setId("B001");

            repository.save(batch);

            repository.delete("B001");

            Batch foundBatch = repository.findById("B001");

            assertNull(foundBatch);
    }
}
