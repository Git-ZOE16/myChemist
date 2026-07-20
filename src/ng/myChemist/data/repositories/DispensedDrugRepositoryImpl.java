package ng.myChemist.data.repositories;

import ng.myChemist.data.models.DispensedDrug;

import java.util.ArrayList;
import java.util.List;

public class DispensedDrugRepositoryImpl implements DispensedDrugRepository {
    private List<DispensedDrug> dispensedDrugs = new ArrayList<>();

    @Override
    public void save(DispensedDrug dispensedDrug) {
        dispensedDrugs.add(dispensedDrug);

    }

    @Override
    public DispensedDrug findById(String id) {
        for(DispensedDrug dispensedDrug : dispensedDrugs){
            if(dispensedDrug.getId().equals(id)){
                return dispensedDrug;
            }
        }
        return null;
    }

    @Override
    public List<DispensedDrug> findAll() {
        return dispensedDrugs;
    }

    @Override
    public void delete(String id) {
        DispensedDrug dispensedDrug = findById(id);

        if(dispensedDrug != null) {
            dispensedDrugs.remove(dispensedDrug);
        }
    }

    @Override
    public int count() {
        return dispensedDrugs.size();
    }
}
