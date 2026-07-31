package ng.myChemist.services;

import ng.myChemist.data.repositories.DrugRepository;
import ng.myChemist.dto.request.*;
import ng.myChemist.dto.response.*;
import org.junit.jupiter.api.Test;
import java.time.YearMonth;
import java.util.List;

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

    @Test
    public void TestToDispenseDrug_BatchQuantityReduces(){
        ChemistService chemistService = new ChemistService();

        AddDrugRequest drugRequest = new AddDrugRequest();

        drugRequest.setDrugName("Paracetamol");

        AddDrugResponse drugResponse = chemistService.addDrug(drugRequest);

        AddBatchRequest batchRequest = new AddBatchRequest();

        batchRequest.setDrugId(drugResponse.getId());

        batchRequest.setQuantity(100);

        AddBatchResponse batchResponse = chemistService.addBatch(batchRequest);

        DispenseDrugRequest dispenseRequest = new DispenseDrugRequest();

        dispenseRequest.setBatchId(batchResponse.getId());
        dispenseRequest.setQuantity(5);

        chemistService.dispenseDrug(dispenseRequest);

        FindBatchResponse response = chemistService.findBatch(batchResponse.getId());

        assertEquals(95, response.getQuantity());
    }

    @Test
    public void TestToDispenseDrug_DispensedDrugIsSaved(){

        ChemistService chemistService = new ChemistService();

        AddDrugRequest drugRequest = new AddDrugRequest();
        drugRequest.setDrugName("Paracetamol");

        AddDrugResponse drugResponse = chemistService.addDrug(drugRequest);

        AddBatchRequest batchRequest = new AddBatchRequest();
        batchRequest.setDrugId(drugResponse.getId());
        batchRequest.setQuantity(100);

        AddBatchResponse batchResponse = chemistService.addBatch(batchRequest);

        DispenseDrugRequest dispenseRequest = new DispenseDrugRequest();
        dispenseRequest.setBatchId(batchResponse.getId());
        dispenseRequest.setQuantity(5);

        DispenseDrugResponse response = chemistService.dispenseDrug(dispenseRequest);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("Drug dispensed Successfully", response.getMessage());

    }

    @Test
    public void TestToFindDispensedDrug_DispensedDrugIsFound(){
        ChemistService chemistService = new ChemistService();

        AddDrugRequest drugRequest = new AddDrugRequest();
        drugRequest.setDrugName("Paracetamol");

        AddDrugResponse drugResponse = chemistService.addDrug(drugRequest);

        AddBatchRequest batchRequest = new AddBatchRequest();
        batchRequest.setDrugId(drugResponse.getId());
        batchRequest.setQuantity(100);

        AddBatchResponse batchResponse = chemistService.addBatch(batchRequest);

        DispenseDrugRequest dispenseRequest = new DispenseDrugRequest();

        dispenseRequest.setBatchId(batchResponse.getId());
        dispenseRequest.setQuantity(5);

        DispenseDrugResponse dispenseResponse = chemistService.dispenseDrug(dispenseRequest);


        FindDispensedDrugResponse response = chemistService.findDispensedDrug(dispenseResponse.getId());

        assertNotNull(response);
        assertEquals(dispenseResponse.getId(), response.getId());
        assertEquals(5, response.getQuantity());
    }

    @Test
    public void TestToFind_AllDispensedDrugs(){
        ChemistService chemistService = new ChemistService();

        AddDrugRequest firstDrug = new AddDrugRequest();
        firstDrug.setDrugName("Paracetamol");

        AddDrugResponse firstDrugResponse = chemistService.addDrug(firstDrug);

        AddBatchRequest firstBatch = new AddBatchRequest();
        firstBatch.setDrugId(firstDrugResponse.getId());
        firstBatch.setQuantity(100);

        AddBatchResponse firstBatchResponse = chemistService.addBatch(firstBatch);

        DispenseDrugRequest dispense1 = new DispenseDrugRequest();
        dispense1.setBatchId(firstBatchResponse.getId());
        dispense1.setQuantity(5);

        chemistService.dispenseDrug(dispense1);



        AddDrugRequest secondDrug = new AddDrugRequest();
        secondDrug.setDrugName("Amatem");

        AddDrugResponse secondDrugResponse = chemistService.addDrug(secondDrug);

        AddBatchRequest secondBatch = new AddBatchRequest();
        secondBatch.setDrugId(firstDrugResponse.getId());
        secondBatch.setQuantity(50);

        AddBatchResponse secondBatchResponse = chemistService.addBatch(secondBatch);

        DispenseDrugRequest dispense2 = new DispenseDrugRequest();
        dispense2.setBatchId(secondBatchResponse.getId());
        dispense2.setQuantity(3);

        chemistService.dispenseDrug(dispense2);

        List<FindDispensedDrugResponse> responses = chemistService.findAllDispensedDrugs();

        assertEquals(2, responses.size());

    }

    @Test
    public void TestToDeleteDispensedDrug_DispensedDrugIsDeleted(){

        ChemistService chemistService = new ChemistService();

        AddDrugRequest drugRequest = new AddDrugRequest();
        drugRequest.setDrugName("Paracetamol");

        AddDrugResponse drugResponse = chemistService.addDrug(drugRequest);

        AddBatchRequest batchRequest = new AddBatchRequest();
        batchRequest.setDrugId(drugResponse.getId());
        batchRequest.setQuantity(100);

        AddBatchResponse batchResponse = chemistService.addBatch(batchRequest);

        DispenseDrugRequest dispenseRequest = new DispenseDrugRequest();

        dispenseRequest.setBatchId(batchResponse.getId());
        dispenseRequest.setQuantity(5);

        DispenseDrugResponse dispenseResponse = chemistService.dispenseDrug(dispenseRequest);


        chemistService.deleteDispensedDrug(dispenseResponse.getId());

        FindDispensedDrugResponse response = chemistService.findDispensedDrug(dispenseResponse.getId());

        assertNull(response);
    }

    @Test
    public void TestToFindAllDrugs_ReturnAllSavedDrugs(){
        ChemistService chemistService = new ChemistService();

        AddDrugRequest firstDrug = new AddDrugRequest();
        firstDrug.setDrugName("Paracetamol");

        AddDrugRequest secondDrug = new AddDrugRequest();
        secondDrug.setDrugName("Amatem");

        chemistService.addDrug(firstDrug);
        chemistService.addDrug(secondDrug);

        List<FindDrugResponse> drugs = chemistService.findAllDrugs();

        assertNotNull(drugs);
        assertEquals(2,drugs.size());

        assertEquals("Paracetamol", drugs.get(0).getDrugName());
        assertEquals("Amatem", drugs.get(1).getDrugName());
    }

    @Test
    public void TestTo_FindAllBatches_ForOneDrug(){

        ChemistService chemistService = new ChemistService();

        AddDrugRequest drugRequest = new AddDrugRequest();
        drugRequest.setDrugName("Paracetamol");

        AddDrugResponse drugResponse = chemistService.addDrug(drugRequest);

        AddBatchRequest firstBatch = new AddBatchRequest();
        firstBatch.setDrugId(drugResponse.getId());
        firstBatch.setExpiryDate(YearMonth.of(2026, 8));
        firstBatch.setQuantity(100);

        AddBatchRequest secondBatch = new AddBatchRequest();
        secondBatch.setDrugId(drugResponse.getId());
        secondBatch.setExpiryDate(YearMonth.of(2027, 12));
        secondBatch.setQuantity(50);

        chemistService.addBatch(firstBatch);
        chemistService.addBatch(secondBatch);

        List<FindBatchResponse> batches = chemistService.findBatchesByDrug(drugResponse.getId());

        assertNotNull(batches);
        assertEquals(2, batches.size());
    }

    @Test
    public void TestToDispenseDrug_WithDrugId(){
        ChemistService chemistService = new ChemistService();

        AddDrugRequest drugRequest = new AddDrugRequest();
        drugRequest.setDrugName("Paracetamol");

        AddDrugResponse drugResponse = chemistService.addDrug(drugRequest);

        AddBatchRequest firstBatch = new AddBatchRequest();
        firstBatch.setDrugId(drugResponse.getId());
        firstBatch.setExpiryDate(YearMonth.of(2026, 8));
        firstBatch.setQuantity(100);

        chemistService.addBatch(firstBatch);

        DispenseDrugByNameRequest request = new DispenseDrugByNameRequest();
        request.setDrugId(drugResponse.getId());
        request.setQuantity(10);

        DispenseDrugResponse response = chemistService.dispenseDrugByDrugId(request);

        assertNotNull(response);
        assertEquals("Drug dispensed Successfully", response.getMessage());
    }

    @Test
    public void TestToDispenseDrugs_AcrossMultipleBatches(){

        ChemistService chemistService = new ChemistService();


    }
}


