package ng.myChemist.data.repositories;

import ng.myChemist.data.models.Batch;

import java.util.List;

public interface BatchRepository {

    void save(Batch batch);

    Batch findById(String id);

    List<Batch> findAll();

    void delete(String id);

    void deleteAll();

    int count();

    List<Batch> findByDrugId(String drugId);
}
