package ng.myChemist.services;

import ng.myChemist.data.repositories.DrugRepository;
import ng.myChemist.dto.request.AddBatchRequest;
import ng.myChemist.dto.request.AddDrugRequest;
import ng.myChemist.dto.request.RegisterUserRequest;
import ng.myChemist.dto.response.*;
import org.junit.jupiter.api.Test;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

public class ChemistServiceTest {

    private ChemistService chemistService;
    private DrugRepository drugRepository;

    @Test
    public void testToRegisterUserAndReturnResponse() {
        ChemistService service = new ChemistService();
        RegisterUserRequest request = new RegisterUserRequest();

        request.setUsername("Oluyemi");
        request.setFullName("Oluyemi Zoe");
        request.setPassword("150122");

        RegisterUserResponse response = service.registerUser(request);
        assertNotNull(response);
    }

    @Test
    public void TestToAddDrug_drugIsSaved() {
        ChemistService chemistService = new ChemistService();

        AddDrugRequest request = new AddDrugRequest();

        request.setDrugName("Paracetamol");
        //request.setPrice(800.00);
        //request.setQuantity(100);

        AddDrugResponse response = chemistService.addDrug(request);

        assertNotNull(response);
        assertEquals("Drug added Successfully", response.getMessage());

    }

    @Test
    public void TestToAddTwoDrugs_DrugsHaveDifferentIds() {
        ChemistService chemistService = new ChemistService();

        AddDrugRequest firstDrug = new AddDrugRequest();
        firstDrug.setDrugName("Paracetamol");
        //firstDrug.setPrice(800.00);
        //firstDrug.setQuantity(100);

        AddDrugRequest secondDrug = new AddDrugRequest();
        secondDrug.setDrugName("Amatem");
        //secondDrug.setPrice(1000.00);
        //secondDrug.setQuantity(20);

        AddDrugResponse firstResponse = chemistService.addDrug(firstDrug);
        AddDrugResponse secondResponse = chemistService.addDrug(secondDrug);

        assertNotNull(firstResponse.getId());
        assertNotNull(secondResponse.getId());

        assertNotEquals(firstResponse.getId(), secondResponse.getId());
    }

    @Test
    public void TestToFindDrug_DrugIsFound() {
        ChemistService chemistService = new ChemistService();

        AddDrugRequest request = new AddDrugRequest();
        request.setDrugName("Paracetamol");
        //request.setPrice(800);
        //request.setQuantity(100);

        AddDrugResponse addDrugResponse = chemistService.addDrug(request);

        String drugId = addDrugResponse.getId();

        FindDrugResponse response = chemistService.findDrug(drugId);

        assertNotNull(response);
        assertEquals("Paracetamol", response.getDrugName());
    }

    @Test
    public void TestToDeleteDrug_drugIsDeleted() {
        ChemistService chemistService = new ChemistService();

        AddDrugRequest request = new AddDrugRequest();
        request.setDrugName("Amatem");
        //request.setPrice(1000.00);
        //request.setQuantity(20);

        AddDrugResponse addDrugResponse = chemistService.addDrug(request);

        String drugId = addDrugResponse.getId();

        chemistService.deleteDrug(drugId);

        FindDrugResponse response = chemistService.findDrug(drugId);
        assertNull(response);
    }

    @Test
    public void TestToAddBatch_BatchIsSaved() {
        ChemistService chemistService = new ChemistService();

        AddDrugRequest drugRequest = new AddDrugRequest();
        drugRequest.setDrugName("Paracetamol");

        AddDrugResponse drugResponse = chemistService.addDrug(drugRequest);

        AddBatchRequest batch = new AddBatchRequest();

        batch.setDrugId(drugResponse.getId());
        batch.setBrand("Emzor");
        batch.setExpiryDate(YearMonth.of(2027, 12));
        batch.setPurchasedDate(YearMonth.now());
        batch.setPrice(800.00);
        batch.setQuantity(100);

        AddBatchResponse batchResponse = chemistService.addBatch(batch);

        assertNotNull(batchResponse);
        assertNotNull(batchResponse.getId());
        assertEquals("Batch added Successfully", batchResponse.getMessage());
    }

    @Test
    public void TestToAddBatch_DrugDoesNotExist() {
        ChemistService chemistService = new ChemistService();

        AddBatchRequest batch = new AddBatchRequest();

        batch.setDrugId("DAAA");
        batch.setBrand("Emzor");
        batch.setExpiryDate(YearMonth.of(2027, 12));
        batch.setPurchasedDate(YearMonth.now());
        batch.setPrice(800.00);
        batch.setQuantity(100);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> chemistService.addBatch(batch));
    }

    @Test
    public void TestToAddTwoBatches_ForSameDrug_BatchesHaveDifferentIds() {
        ChemistService chemistService = new ChemistService();

        AddDrugRequest drugRequest = new AddDrugRequest();
        drugRequest.setDrugName("Paracetamol");

        AddDrugResponse drugResponse = chemistService.addDrug(drugRequest);

        AddBatchRequest batchOne = new AddBatchRequest();
        batchOne.setDrugId(drugResponse.getId());
        batchOne.setBrand("Emzor");
        batchOne.setExpiryDate(YearMonth.of(2027, 12));
        batchOne.setPurchasedDate(YearMonth.now());
        batchOne.setPrice(800.00);
        batchOne.setQuantity(100);

        AddBatchRequest batchTwo = new AddBatchRequest();
        batchTwo.setDrugId(drugResponse.getId());
        batchTwo.setBrand("Fidson");
        batchTwo.setExpiryDate(YearMonth.of(2028, 6));
        batchTwo.setPurchasedDate(YearMonth.now());
        batchTwo.setPrice(850.00);
        batchTwo.setQuantity(50);

        AddBatchResponse batchOneResponse = chemistService.addBatch(batchOne);
        AddBatchResponse batchTwoResponse = chemistService.addBatch(batchTwo);

        assertNotNull(batchOneResponse);
        assertNotNull(batchTwoResponse);

        assertNotNull(batchOneResponse.getId());
        assertNotNull(batchTwoResponse.getId());

        assertNotEquals(batchOneResponse.getId(), batchTwoResponse.getId());
    }

    @Test
    public void TestToFindOneBatch_BatchIsFound(){

        ChemistService chemistService = new ChemistService();

        AddDrugRequest drugRequest = new AddDrugRequest();
        drugRequest.setDrugName("Paracetamol");

        AddDrugResponse drugResponse = chemistService.addDrug(drugRequest);

        AddBatchRequest batchRequest = new AddBatchRequest();

        batchRequest.setDrugId(drugResponse.getId());
        batchRequest.setBrand("Emzor");
        batchRequest.setExpiryDate(YearMonth.of(2027, 12));
        batchRequest.setPurchasedDate(YearMonth.now());
        batchRequest.setPrice(800.00);
        batchRequest.setQuantity(100);

        AddBatchResponse addBatchResponse = chemistService.addBatch(batchRequest);
        String batchId = addBatchResponse.getId();
        FindBatchResponse response = chemistService.findBatch(batchId);

        assertNotNull(response);
        assertEquals(batchId, response.getId());
        assertEquals("Paracetamol", response.getDrugName());
        assertEquals("Emzor", response.getBrand());
    }

    @Test
    public void TestToFindBatch_BatchDoesNotExist(){
        ChemistService chemistService = new ChemistService();

        FindBatchResponse response = chemistService.findBatch("B888");

        assertNull(response);
    }

    @Test
    public void TestToDeleteBatch_BatchIsDeleted(){
        ChemistService chemistService = new ChemistService();

        AddDrugRequest drugRequest = new AddDrugRequest();
        drugRequest.setDrugName("Paracetamol");

        AddDrugResponse drugResponse = chemistService.addDrug(drugRequest);

        AddBatchRequest batchRequest = new AddBatchRequest();

        batchRequest.setDrugId(drugResponse.getId());
        batchRequest.setBrand("Emzor");
        batchRequest.setExpiryDate(YearMonth.of(2027, 12));
        batchRequest.setPurchasedDate(YearMonth.now());
        batchRequest.setPrice(800.00);
        batchRequest.setQuantity(100);

        AddBatchResponse batchResponse = chemistService.addBatch(batchRequest);

        String batchId = batchResponse.getId();

        chemistService.deleteBatch(batchId);

        FindBatchResponse response = chemistService.findBatch(batchId);

        assertNull(response);

    }

    @Test
    public void TestToDeleteOneBatch_OtherBatchesStillExist(){
        ChemistService chemistService = new ChemistService();

        AddDrugRequest drugRequest = new AddDrugRequest();
        drugRequest.setDrugName("Paracetamol");

        AddDrugResponse drugResponse = chemistService.addDrug(drugRequest);

        AddBatchRequest batchOne = new AddBatchRequest();
        batchOne.setDrugId(drugResponse.getId());
        batchOne.setBrand("Emzor");
        batchOne.setExpiryDate(YearMonth.of(2027, 12));
        batchOne.setPurchasedDate(YearMonth.now());
        batchOne.setPrice(800.00);
        batchOne.setQuantity(100);


        AddBatchRequest batchTwo = new AddBatchRequest();
        batchTwo.setDrugId(drugResponse.getId());
        batchTwo.setBrand("Fidson");
        batchTwo.setExpiryDate(YearMonth.of(2028, 6));
        batchTwo.setPurchasedDate(YearMonth.now());
        batchTwo.setPrice(850.00);
        batchTwo.setQuantity(50);

        AddBatchResponse BatchOneResponse = chemistService.addBatch(batchOne);

        AddBatchResponse BatchTwoResponse = chemistService.addBatch(batchTwo);

        chemistService.deleteBatch(BatchOneResponse.getId());

        FindBatchResponse deletedBatch = chemistService.findBatch(BatchOneResponse.getId());

        FindBatchResponse remainingBatches = chemistService.findBatch(BatchTwoResponse.getId());

        assertNull(deletedBatch);
        assertNotNull(remainingBatches);
    }
}

