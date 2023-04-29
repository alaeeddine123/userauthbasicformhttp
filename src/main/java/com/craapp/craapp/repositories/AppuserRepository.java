package com.craapp.craapp.repositories;


import com.craapp.craapp.entities.Appuser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("users")
public interface AppuserRepository extends JpaRepository<Appuser,String> {
     Optional<Appuser> findByEmail(String email);
      Appuser findByUsernameOrEmail(String email , String username);
    List<Appuser> findByUsernameContains(String keyword);
    Optional<Appuser> findByUsername(String username);
    List<Appuser> findAll();
}
