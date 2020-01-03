package api.erp.banqueservice.repositories;

import api.erp.banqueservice.model.Agent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends CrudRepository<Agent,Long> {
    Agent findByUsername(String username);
    Agent getById(Long id );
    void deleteById(Long id );
    List<Agent> findAll();
    List<Agent> findByAgence(Long idAgence ) ;
}
