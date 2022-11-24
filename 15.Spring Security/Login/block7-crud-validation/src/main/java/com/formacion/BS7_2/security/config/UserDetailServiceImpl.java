package com.formacion.BS7_2.security.config;

import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.person.infraestructure.dto.repository.PersonDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PersonDaoRepository personRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Person person = personRepo.findByUsername(username);
        if(person ==null){
            throw  new UsernameNotFoundException("the username with  the " + username + "does not exist ");
        }
        return new UserDetailsImpl(person);


    }
}
