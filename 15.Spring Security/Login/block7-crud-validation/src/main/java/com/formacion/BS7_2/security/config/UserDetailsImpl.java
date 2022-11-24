package com.formacion.BS7_2.security.config;

import com.formacion.BS7_2.person.domain.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final Person person;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.emptyList(); //esto se usa si el usuario tiene roles revisar esta parte...
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    //este metodo es para obtener el nombre de la persona
    public String getNombre(){
        return person.getName();
    }
}
