package api.erp.banqueservice.repositories;

import api.erp.banqueservice.model.Abonne;
import org.springframework.data.repository.CrudRepository;

public interface AbonneRepository extends CrudRepository<Abonne,Long> {
    Abonne getById(Long id );
}
