package api.erp.banqueservice.repositories;


import api.erp.banqueservice.model.Agence;
import api.erp.banqueservice.model.Agent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgenceRepository extends CrudRepository<Agence,Long> {

    Agence getById(Long id );
    void deleteById(Long id );
    List<Agence> findAll();
}
