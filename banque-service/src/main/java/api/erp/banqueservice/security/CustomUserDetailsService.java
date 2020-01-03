package api.erp.banqueservice.security;

import api.erp.banqueservice.model.Agent;
import api.erp.banqueservice.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AgentRepository userRepository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Agent user = userRepository.findByUsername(username);
        if(user==null) new UsernameNotFoundException("User not found");
        return user;
    }


    @Transactional
    public Agent loadUserById(Long id){
        Agent user = userRepository.getById(id);
        if(user==null) new UsernameNotFoundException("User not found");
        return user;

    }
}

