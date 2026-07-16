package ng.myChemist.data.repositories;

import ng.myChemist.data.models.DispensedDrug;

import java.util.List;

public interface DispensedDrugRepository {

    void save(DispensedDrug DispensedDrug);
    DispensedDrug findById(String id);
    List<DispensedDrug> findAll();
    void delete(String id);
    int count();

}