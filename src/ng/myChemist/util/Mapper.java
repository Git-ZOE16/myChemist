package ng.myChemist.util;

import ng.myChemist.data.models.Batch;
import ng.myChemist.data.models.DispensedDrug;
import ng.myChemist.data.models.Drug;
import ng.myChemist.data.models.User;
import ng.myChemist.dto.request.*;
import ng.myChemist.dto.response.FindBatchResponse;
import ng.myChemist.dto.response.*;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static User map(RegisterUserRequest request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setPassword(request.getPassword());

        return user;
    }

    public static RegisterUserResponse map(User user) {
        RegisterUserResponse response = new RegisterUserResponse();

        response.setId(user.getId());
        response.setMessage("User registered Successfully");

        return response;

    }

    public static Drug map(AddDrugRequest request) {
        Drug drug = new Drug();

        drug.setName(request.getDrugName());
        return drug;
    }

    public static AddDrugResponse mapToAddDrugResponse(Drug drug) {
        AddDrugResponse response = new AddDrugResponse();

        response.setId(drug.getId());
        response.setMessage("Drug added Successfully");
        return response;
    }

    public static FindDrugResponse mapToFindDrugResponse(Drug drug) {
        if (drug == null) {
            return null;
        }
        FindDrugResponse response = new FindDrugResponse();

        response.setId(drug.getId());
        response.setDrugName(drug.getName());
        return response;
    }

    public static Batch map(AddBatchRequest request, Drug drug) {
        Batch batch = new Batch();

        batch.setDrug(drug);
        batch.setBrand(request.getBrand());
        batch.setExpiryDate(request.getExpiryDate());
        batch.setPurchasedDate(request.getPurchasedDate());
        batch.setPrice(request.getPrice());
        batch.setQuantity(request.getQuantity());

        return batch;
    }

    public static AddBatchResponse mapToAddBatchResponse(Batch batch) {
        AddBatchResponse response = new AddBatchResponse();

        response.setId(batch.getId());
        response.setMessage("Batch added Successfully");

        return response;
    }

    public static FindBatchResponse mapToFindBatchResponse(Batch batch) {

        if (batch == null) {
            return null;
        }
        FindBatchResponse response = new FindBatchResponse();

        response.setId(batch.getId());
        response.setDrugName(batch.getDrug().getName());
        response.setBrand(batch.getBrand());
        response.setExpiryDate(batch.getExpiryDate());
        response.setPurchasedDate(batch.getPurchasedDate());
        response.setPrice(batch.getPrice());
        response.setQuantity(batch.getQuantity());

        return response;
    }

    public static DispensedDrug map(DispenseDrugRequest request, Batch batch){

        DispensedDrug dispensedDrug = new DispensedDrug();

        dispensedDrug.setBatch(batch);
        dispensedDrug.setPrice(batch.getPrice());
        dispensedDrug.setQuantity(request.getQuantity());

        return dispensedDrug;
    }

    public static FindDispensedDrugResponse mapToFindDispensedDrugResponse(DispensedDrug dispensedDrug){
        if(dispensedDrug == null){
            return null;
        }
        FindDispensedDrugResponse response = new FindDispensedDrugResponse();

        response.setId(dispensedDrug.getId());
        response.setQuantity(dispensedDrug.getQuantity());
        response.setPrice(dispensedDrug.getPrice());

        response.setDrugName(dispensedDrug.getBatch().getDrug().getName());

        return response;
    }

    public static List<FindDrugResponse> mapToFindDrugResponse(List<Drug> drugs){
        List<FindDrugResponse> responses = new ArrayList<>();

        for(Drug drug : drugs){
            responses.add(mapToFindDrugResponse(drug));
        }
        return responses;
    }

    public static List<FindBatchResponse> mapToFindBatchResponses(List<Batch> batches){

        List<FindBatchResponse> responses = new ArrayList<>();

        for (Batch batch : batches){
            responses.add(mapToFindBatchResponse(batch));
        }
        return responses;
    }

    public static DispensedDrug map(DispenseDrugByNameRequest request, Batch batch){

        DispensedDrug dispensedDrug = new DispensedDrug();

        dispensedDrug.setBatch(batch);
        dispensedDrug.setQuantity(request.getQuantity());

        return dispensedDrug;
    }
}