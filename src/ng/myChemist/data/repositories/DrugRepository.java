package ng.myChemist.data.repositories;

import ng.myChemist.data.models.Drug;

import java.util.List;

public interface DrugRepository {

    void save(Drug drug);
    Drug findById(String id);
    List<Drug> findAll();
    void delete(String id);
    int count();

}
