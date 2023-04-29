package com.craapp.craapp.entities.SecAppUser;

import com.craapp.craapp.entities.Appuser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;


public class SecurityUser implements UserDetails {


    private   Appuser appuser;

    public SecurityUser(Appuser appuser) {
        this.appuser = appuser;
    }

    public SecurityUser() {
    }

    @Override
    public String getUsername() {
        return appuser.getUsername();
    }

    @Override
    public String getPassword() {
        return appuser.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(appuser
                         .getRoles()
                         .split(","))
                         .map(SimpleGrantedAuthority::new)
                         .toList();
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
}
