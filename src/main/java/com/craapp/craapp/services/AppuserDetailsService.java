package com.craapp.craapp.services;

import com.craapp.craapp.entities.SecAppUser.SecurityUser;
import com.craapp.craapp.repositories.AppuserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AppuserDetailsService implements UserDetailsService{


    private final AppuserRepository appuserRepository;

    public AppuserDetailsService(AppuserRepository appuserRepository) {
        this.appuserRepository = appuserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appuserRepository
                .findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));

                //new SecurityUser(username) ;
    }
}





