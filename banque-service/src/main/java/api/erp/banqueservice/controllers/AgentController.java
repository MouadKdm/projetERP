package api.erp.banqueservice.controllers;

import api.erp.banqueservice.model.*;
import api.erp.banqueservice.payload.JWTLoginSucessReponse;
import api.erp.banqueservice.payload.LoginRequest;
import api.erp.banqueservice.repositories.AgentRepository;
import api.erp.banqueservice.repositories.CompteRepository;
import api.erp.banqueservice.security.JwtTokenProvider;
import api.erp.banqueservice.security.SecurityConstants;
import api.erp.banqueservice.services.AgentService;
import api.erp.banqueservice.services.MapValidationErrorService;
import api.erp.banqueservice.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/agent")
@CrossOrigin

public class AgentController {

    @Autowired
    private AgentService agentService ;
    @Autowired
    private AgentRepository agentRepository ;
    @Autowired
    private CompteRepository compteRepository ;


    @Autowired
    private MapValidationErrorService mapValidationErrorService ;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


//    @PostMapping("add/{id}")
//    public ResponseEntity<?> addAgent(@RequestBody Agent agent,@PathVariable Long id){
//        Agent agent1 = agentService.createAgent(agent,id) ;
//
//        return new ResponseEntity<Agent>(agent1, HttpStatus.CREATED);
//
//    }


    @PostMapping("addAbonne")
    public ResponseEntity<?> addAbonne(@RequestBody Abonne abonne){
        try {


            String theUrl = "http://localhost:8081/user/register";


            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Abonne> requestEntity = new HttpEntity<>(abonne);

            ResponseEntity<Abonne> response = restTemplate.exchange(theUrl, HttpMethod.POST, requestEntity, Abonne.class);

             Abonne abonne1 = response.getBody();
            return new ResponseEntity<Abonne>(abonne1, HttpStatus.CREATED);


        }catch (HttpStatusCodeException ex){
            return new ResponseEntity<String>(ex.getResponseBodyAsString(),HttpStatus.OK);

        }
    }
    @GetMapping("getContracts")
    public  ResponseEntity<?> getAllContracts(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Contract[]> responseEntity = restTemplate.getForEntity("http://localhost:8084/contract/allContracts"
                ,Contract[].class);
//        List<Contract>contracts =restTemplate.getForObject("http://localhost:8084/contract/allContracts",Contract.class);
        Contract[] contracts = responseEntity.getBody();
        List<Contract> contracts1 = new ArrayList<>();
        for (Contract c : contracts) {
            contracts1.add(c);
        }

        return new ResponseEntity<List<Contract>>(contracts1,HttpStatus.OK);
    }
    @GetMapping("getUsers")
    public  ResponseEntity<?> getAllUsers(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Abonne[]> responseEntity = restTemplate.getForEntity("http://localhost:8081/user/getAllUsers"
                ,Abonne[].class);
//        List<Contract>contracts =restTemplate.getForObject("http://localhost:8084/contract/allContracts",Contract.class);
        Abonne[] abonnes = responseEntity.getBody();
        List<Abonne> abonnes1 = new ArrayList<>();
        for (Abonne a : abonnes) {
            abonnes1.add(a);
        }

        return new ResponseEntity<List<Abonne>>(abonnes1,HttpStatus.OK);
    }

//    @PostMapping("addAbonne")
//    public ResponseEntity<?> addAbonne(@RequestBody Abonne abonne){
//
//        Abonne abonne1 = agentService.createAbonne(abonne) ;
//
//        return new ResponseEntity<Abonne>(abonne1, HttpStatus.CREATED);
//
//    }
    @PostMapping("addCompte/{username}")
    public ResponseEntity<?> addCompte(@RequestBody Compte compte, @PathVariable String username){
//        Agent agent = new Agent();
//        agentRepository.save(agent);
//
//        contract.setAgent(agent);
        try {


            String theUrl = "http://localhost:8081/user/addCompte/"+username;
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Compte> requestEntity = new HttpEntity<>(compte);

            ResponseEntity<Compte> response = restTemplate.exchange(theUrl, HttpMethod.POST, requestEntity, Compte.class);

            Compte compte1 = response.getBody();

//        compteRepository.save(compte1);

            return new ResponseEntity<Compte>(compte1, HttpStatus.CREATED);
        }catch (HttpStatusCodeException ex){
            return new ResponseEntity<String>(ex.getResponseBodyAsString(),HttpStatus.OK);

        }

    }


    @PostMapping("addContract/{username}")
    public ResponseEntity<?> addContract(@RequestBody Contract contract, @PathVariable String username, Principal principal){
        Agent agent = agentRepository.findByUsername(principal.getName());
        agentRepository.save(agent);

        contract.setAgent(agent);

        String theUrl = "http://localhost:8084/contract/add/"+username;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Contract> requestEntity = new HttpEntity<>(contract);

        ResponseEntity<Contract> response = restTemplate.exchange(theUrl, HttpMethod.POST, requestEntity, Contract.class );

        Contract contract1 =response.getBody();

        return new ResponseEntity<Contract>(contract1, HttpStatus.CREATED);

    }
//    @PostMapping("addContract/{id}")
//    public ResponseEntity<?> addContract(@RequestBody Contract contract, @PathVariable Long id, Principal principal){
//        Agent agent = agentRepository.findByUsername(principal.getName());
//        agentRepository.save(agent);
//
//        contract.setAgent(agent);
//
//        String theUrl = "http://localhost:8084/contract/add/"+id;
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<Contract> requestEntity = new HttpEntity<>(contract);
//
//        ResponseEntity<Contract> response = restTemplate.exchange(theUrl, HttpMethod.POST, requestEntity, Contract.class );
//
//        Contract contract1 =response.getBody();
//
//        return new ResponseEntity<Contract>(contract1, HttpStatus.CREATED);
//
//    }
//    @PostMapping("addCarteBanquaire/{compteNumber}")
//    public ResponseEntity<?> addCarteBanquaire(@RequestBody CarteBanquaire carteBanquaire, @PathVariable String compteNumber){
//
//
//        String theUrl = "http://localhost:8081/user/addCarteBanquaire/"+compteNumber;
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<CarteBanquaire> requestEntity = new HttpEntity<>(carteBanquaire);
//
//        ResponseEntity<CarteBanquaire> response = restTemplate.exchange(theUrl, HttpMethod.POST, requestEntity, CarteBanquaire.class );
//
//        CarteBanquaire carteBanquaire1 =response.getBody();
//
//        return new ResponseEntity<CarteBanquaire>(carteBanquaire1, HttpStatus.CREATED);
//
//    }



    @GetMapping("id/{id}")
    public ResponseEntity<?> getAgentById(@PathVariable Long id){
        Agent agent1 = agentService.findAgentById(id) ;

        return new ResponseEntity<Agent>(agent1, HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable Long id){
          agentService.deleteAgentById(id) ;

        return new ResponseEntity<String>("agent with id "+id+" was deleted with success", HttpStatus.OK);

    }

    @GetMapping("allAgent")
    public ResponseEntity<?> getAllAgent(){
        List<Agent> agents = agentService.findAllAgent() ;

        return new ResponseEntity<List<Agent>>(agents, HttpStatus.OK);

    }

    @GetMapping("agents/{idAgence}")
    public ResponseEntity<?> getAllAgentByAgence(@PathVariable Long idAgence){
        List<Agent> agents = agentService.findAllAgentByAgence(idAgence) ;

        return new ResponseEntity<List<Agent>>(agents, HttpStatus.OK);

    }
    @GetMapping("getCompte/{compteNumber}")
    public ResponseEntity<?> getCompte(@PathVariable String compteNumber){
        RestTemplate restTemplate = new RestTemplate();
        Compte compte = restTemplate.getForObject("http://localhost:8082/compte/compteNumber/"+compteNumber,Compte.class);


        return new ResponseEntity<Compte>(compte, HttpStatus.OK);

    }


}
