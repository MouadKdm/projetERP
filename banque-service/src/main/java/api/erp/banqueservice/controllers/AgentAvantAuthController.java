package api.erp.banqueservice.controllers;

import api.erp.banqueservice.model.Agent;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/agentAvAuth")
@CrossOrigin

public class AgentAvantAuthController {

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

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        Agent user = agentRepository.findByUsername(loginRequest.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX +  tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Agent user , BindingResult result){
        userValidator.validate(user,result);
        ResponseEntity errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap ;
        Agent user1 = agentService.saveOrUpdateUser(user);
        return new ResponseEntity<Agent>(user1, HttpStatus.CREATED);
    }
}
