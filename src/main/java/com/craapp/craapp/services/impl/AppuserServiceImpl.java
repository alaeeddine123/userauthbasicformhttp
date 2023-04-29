package com.craapp.craapp.services.impl;

import com.craapp.craapp.entities.Appuser;
import com.craapp.craapp.repositories.AppuserRepository;
import com.craapp.craapp.services.AppuserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AppuserServiceImpl  implements AppuserService {


    private final AppuserRepository appuserRepository;

    public AppuserServiceImpl(AppuserRepository appuserRepository) {
        this.appuserRepository = appuserRepository;
    }


    @Override
    public Optional<Appuser> findByUsername(String username) {
        return appuserRepository.findByUsername(username) ;
    }

    @Override
    public List<Appuser> findAll() {
        return appuserRepository.findAll();
    }


}
