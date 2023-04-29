package com.craapp.craapp.services;


import com.craapp.craapp.entities.Appuser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppuserService {

    Optional<Appuser> findByUsername(String username);

    public List<Appuser> findAll();

}
