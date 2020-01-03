package api.erp.banqueservice.repositories;

import api.erp.banqueservice.model.Compte;
import org.springframework.data.repository.CrudRepository;

public interface CompteRepository extends CrudRepository<Compte,Long> {
}
