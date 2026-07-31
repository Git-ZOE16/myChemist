package ng.myChemist.data.repositories;

import ng.myChemist.data.models.Batch;
import ng.myChemist.data.models.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugRepositoryImpl implements DrugRepository {
    private List<Drug> drugs = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void save(Drug drug) {

        if(drug.getId() == null){
            drug.setId(String.valueOf(nextId));
            nextId++;
        }
        drugs.add(drug);

    }

    @Override
    public Drug findById(String id) {
        for (Drug drug : drugs) {
            if (drug.getId().equals(id)) {
                return drug;
            }
        }
        return null;
    }

    @Override
    public List<Drug> findAll() {
        return drugs;
    }

    @Override
    public void delete(String id) {
        Drug drug = findById(id);

        if (drug != null) {
            drugs.remove(drug);
        }
    }

    @Override
    public int count() {
        return drugs.size();
    }

}

