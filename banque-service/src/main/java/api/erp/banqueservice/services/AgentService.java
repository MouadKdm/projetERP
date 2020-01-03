package api.erp.banqueservice.services;

import api.erp.banqueservice.exceptions.UsernameAlreadyExistsException;
import api.erp.banqueservice.model.Abonne;
import api.erp.banqueservice.model.Agence;
import api.erp.banqueservice.model.Agent;
import api.erp.banqueservice.repositories.AbonneRepository;
import api.erp.banqueservice.repositories.AgenceRepository;
import api.erp.banqueservice.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository ;
    @Autowired
    private AgenceRepository agenceRepository ;
    @Autowired
    private AbonneRepository abonneRepository ;

//    public Agent createAgent(Agent agent, Long id){
//        Agence agence = agenceRepository.getById(id);
//        agent.setAgence(agence);
//        Agent agent1 = agentRepository.save(agent);
//        return agent1 ;
//    }
        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder ;

    public Agent saveOrUpdateUser(Agent user){
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setUsername((user.getUsername()));
            user.setConfirmPassword("");
            return agentRepository.save(user);
        }catch(Exception e){
            throw new UsernameAlreadyExistsException("username" + user.getUsername() + " already exist");
        }

    }

    public Agent findAgentById(Long id ){
        Agent agent = agentRepository.getById(id);
        return agent ;
    }

    public void deleteAgentById(Long  id ){

        agentRepository.deleteById(id);
    }

    public List<Agent> findAllAgent(){
        List<Agent> agents = agentRepository.findAll();
        return agents ;
    }
    public List<Agent> findAllAgentByAgence(Long idAgence){
        List<Agent> agents = agentRepository.findByAgence(idAgence);
        return agents ;
    }

    public Abonne createAbonne(Abonne abonne){
        return abonneRepository.save(abonne);
    }
    public Abonne findAbonneById(Long id){
        return abonneRepository.getById(id);
    }
}
