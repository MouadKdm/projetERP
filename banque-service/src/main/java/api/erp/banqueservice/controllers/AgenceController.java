package api.erp.banqueservice.controllers;


import api.erp.banqueservice.model.Agence;
import api.erp.banqueservice.services.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agence")
public class AgenceController {

    @Autowired
    private AgenceService agenceService ;

    @PostMapping("add")
    public ResponseEntity<?> addAgent(@RequestBody Agence agence){
        Agence agent1 = agenceService.createAgence(agence) ;

        return new ResponseEntity<Agence>(agent1, HttpStatus.CREATED);

    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getAgenceById(@PathVariable Long id){
        Agence agent1 = agenceService.findAgenceById(id) ;

        return new ResponseEntity<Agence>(agent1, HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteAgence(@PathVariable Long id){
        agenceService.deleteAgenceById(id) ;

        return new ResponseEntity<String>("agent with id "+id+" was deleted with success", HttpStatus.OK);

    }

    @GetMapping("allAgent")
    public ResponseEntity<?> getAllAgent(){
        List<Agence> agences = agenceService.findAllAgence() ;

        return new ResponseEntity<List<Agence>>(agences, HttpStatus.OK);

    }
}
