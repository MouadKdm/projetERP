package api.erp.banqueservice.services;


import api.erp.banqueservice.model.Agence;
import api.erp.banqueservice.repositories.AgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenceService {

    @Autowired
    private AgenceRepository agenceRepository ;

    public Agence createAgence(Agence agence){
        Agence agence1 = agenceRepository.save(agence);
        return agence1 ;
    }

    public Agence findAgenceById(Long id ){
        Agence agent = agenceRepository.getById(id);
        return agent ;
    }

    public void deleteAgenceById(Long  id ){

        agenceRepository.deleteById(id);
    }

    public List<Agence> findAllAgence(){
        List<Agence> agences = agenceRepository.findAll();
        return agences ;
    }
}
