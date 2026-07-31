package ng.myChemist.services;

import ng.myChemist.data.models.*;
import ng.myChemist.data.repositories.*;
import ng.myChemist.dto.request.*;
import ng.myChemist.dto.response.*;

import ng.myChemist.util.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ChemistService {

    private UserRepository userRepository = new UserRepositoryImpl();
    private DrugRepository drugRepository = new DrugRepositoryImpl();
    private BatchRepository batchRepository = new BatchRepositoryImpl();
    private DispensedDrugRepository dispensedDrugRepository = new DispensedDrugRepositoryImpl();

    public RegisterUserResponse registerUser (RegisterUserRequest request){
        User user = Mapper.map(request);
        userRepository.save(user);
        return Mapper.map(user);
    }

    public AddDrugResponse addDrug (AddDrugRequest request){
        Drug drug = Mapper.map(request);
        drugRepository.save(drug);
        return Mapper.mapToAddDrugResponse(drug);
    }

    public FindDrugResponse findDrug (String id){
        Drug drug = drugRepository.findById(id);
        if (drug == null) {
            return null;
        }
        return Mapper.mapToFindDrugResponse(drug);
    }

        public void deleteDrug (String id){

        drugRepository.delete(id);
    }

    public AddBatchResponse addBatch (AddBatchRequest request){
        Drug drug = drugRepository.findById(request.getDrugId());
        if (drug == null) {
            throw new RuntimeException("Drug not found");
        }
        Batch batch = Mapper.map(request, drug);

        batchRepository.save(batch);

        return Mapper.mapToAddBatchResponse(batch);
    }

    public FindBatchResponse findBatch (String id){

        Batch batch = batchRepository.findById(id);
        if (batch == null) {
            return null;
        }
        return Mapper.mapToFindBatchResponse(batch);
    }

    public void deleteBatch (String id){
        batchRepository.delete(id);
    }

    public DispenseDrugResponse dispenseDrug(DispenseDrugRequest request){

        Batch batch = batchRepository.findById(request.getBatchId());

        if(batch == null){
            throw new RuntimeException("Batch not found");
        }

        if(request.getQuantity() <= 0){
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if(request.getQuantity() > batch.getQuantity()){
            throw new RuntimeException("Insufficient quantity in batch");
        }
        batch.setQuantity(batch.getQuantity() - request.getQuantity());

        DispensedDrug dispensedDrug = Mapper.map(request, batch);

        dispensedDrugRepository.save(dispensedDrug);

        DispenseDrugResponse response = new DispenseDrugResponse();

        response.setId(dispensedDrug.getId());

        response.setMessage("Drug dispensed Successfully");

        return response;
    }


    public FindDispensedDrugResponse findDispensedDrug(String id){

        DispensedDrug dispensedDrug = dispensedDrugRepository.findById(id);

        if(dispensedDrug == null){
            return null;
        }
      return Mapper.mapToFindDispensedDrugResponse(dispensedDrug);
    }


    public List<FindDispensedDrugResponse> findAllDispensedDrugs(){

        List<DispensedDrug> dispensedDrugs = dispensedDrugRepository.findAll();
        List<FindDispensedDrugResponse> responses = new ArrayList<>();

        for (DispensedDrug dispensedDrug : dispensedDrugs){
            responses.add(Mapper.mapToFindDispensedDrugResponse(dispensedDrug));
        }
        return responses;
    }


    public void deleteDispensedDrug(String id){
        dispensedDrugRepository.delete(id);
    }


    public List<FindDrugResponse> findAllDrugs(){

        List<Drug> drugs = drugRepository.findAll();

        return Mapper.mapToFindDrugResponse(drugs);
    }


    public List<FindBatchResponse> findBatchesByDrug(String drugId){

        List<Batch> batches = batchRepository.findByDrugId(drugId);

        return Mapper.mapToFindBatchResponses(batches);
    }


    public DispenseDrugResponse dispenseDrugByDrugId(DispenseDrugByNameRequest request){

        List<Batch> batches = batchRepository.findByDrugId(request.getDrugId());

        if(batches.isEmpty()){
            throw new RuntimeException("No batches found for this drug");
        }

        batches.sort((first, second) -> first.getExpiryDate().compareTo(second.getExpiryDate()));

        Batch batch = batches.get(0);

        if(request.getQuantity() > batch.getQuantity()){
            throw new RuntimeException("Insufficient quantity");
        }


        batch.setQuantity(batch.getQuantity() - request.getQuantity());

        DispensedDrug dispensedDrug = Mapper.map(request, batch);

        dispensedDrugRepository.save(dispensedDrug);

        DispenseDrugResponse response = new DispenseDrugResponse();

        response.setId(dispensedDrug.getId());
        response.setMessage("Drug dispensed Successfully");

        return response;
    }

}
