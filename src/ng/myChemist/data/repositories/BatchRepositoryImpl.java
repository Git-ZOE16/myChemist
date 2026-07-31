package ng.myChemist.data.repositories;

import ng.myChemist.data.models.Batch;

import java.util.ArrayList;
import java.util.List;

public class BatchRepositoryImpl implements BatchRepository {
    private List<Batch> batches = new ArrayList<>();

@Override
    public void save(Batch batch){
    String id = String.format("B%03d", batches.size() + 1);
    batch.setId(id);
    batches.add(batch);
}

@Override
  public Batch findById(String id){
    for(Batch batch : batches){
        if(batch.getId().equals(id)){
            return batch;
        }
    }
    return null;
}

@Override
    public List<Batch> findAll(){
        return batches;
}

@Override
    public void delete(String id){
        Batch batch = findById(id);
        if(batch != null){
            batches.remove(batch);
        }
}

@Override
    public void deleteAll(){
        batches.clear();
}


@Override
    public int count(){

    return batches.size();
}

@Override
    public List<Batch> findByDrugId(String drugId){

    List<Batch> batchesForDrug = new ArrayList<>();

    for (Batch batch : batches) {
        if (batch.getDrug().getId().equals(drugId)){
            batchesForDrug.add(batch);
        }
    }
    return batchesForDrug;
}

}
