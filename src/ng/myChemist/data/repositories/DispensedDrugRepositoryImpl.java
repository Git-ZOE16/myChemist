package ng.myChemist.data.repositories;

import ng.myChemist.data.models.DispensedDrug;

import java.util.ArrayList;
import java.util.List;

public class DispensedDrugRepositoryImpl implements DispensedDrugRepository {
    private List<DispensedDrug> DispensedDrugs = new ArrayList<>();

    @Override
    public void save(DispensedDrug DispensedDrug) {
        DispensedDrugs.add(DispensedDrug);

    }

    @Override
    public DispensedDrug findById(String id) {
        for(DispensedDrug DispensedDrug : DispensedDrugs){
            if(DispensedDrug.getId().equals(id)){
                return DispensedDrug;
            }
        }
        return null;
    }

    @Override
    public List<DispensedDrug> findAll() {
        return DispensedDrugs;
    }

    @Override
    public void delete(String id) {
        DispensedDrug DispensedDrug = findById(id);

        if(DispensedDrug != null) {
            DispensedDrugs.remove(DispensedDrug);
        }
    }

    @Override
    public int count() {
        return DispensedDrugs.size();
    }
}
