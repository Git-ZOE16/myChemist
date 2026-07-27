package ng.myChemist.services;

import ng.myChemist.data.models.Drug;
import ng.myChemist.data.models.User;
import ng.myChemist.data.models.Batch;
import ng.myChemist.data.repositories.*;
import ng.myChemist.dto.request.AddBatchRequest;
import ng.myChemist.dto.request.AddDrugRequest;
import ng.myChemist.dto.request.RegisterUserRequest;
import ng.myChemist.dto.response.*;

import ng.myChemist.util.Mapper;

public class ChemistService {

    private UserRepository userRepository = new UserRepositoryImpl();
    private DrugRepository drugRepository = new DrugRepositoryImpl();
    private BatchRepository batchRepository = new BatchRepositoryImpl();

    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        User user = Mapper.map(request);
        userRepository.save(user);
        return Mapper.map(user);
    }

    public AddDrugResponse addDrug(AddDrugRequest request){
        Drug drug = Mapper.map(request);
        drugRepository.save(drug);
        return Mapper.mapToAddDrugResponse(drug);
    }

    public FindDrugResponse findDrug(String id){
        Drug drug = drugRepository.findById(id);
        if(drug == null){
            return null;
        }
        return Mapper.mapToFindDrugResponse(drug);
    }

    public void deleteDrug(String id){

        drugRepository.delete(id);
    }

    public AddBatchResponse addBatch(AddBatchRequest request){
        Drug drug = drugRepository.findById(request.getDrugId());
        if(drug == null){
            throw new RuntimeException("Drug not found");
        }
        Batch batch = Mapper.map(request, drug);

        batchRepository.save(batch);

        return Mapper.mapToAddBatchResponse(batch);
    }

    public FindBatchResponse findBatch(String id){

        Batch batch = batchRepository.findById(id);
        if(batch == null){
            return null;
        }
        return Mapper.mapToFindBatchResponse(batch);
    }

    public void deleteBatch(String id){
        batchRepository.delete(id);
    }
}