package com.jhon.startup.repository;

import com.jhon.startup.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


/**
 * Esta interactua con la base de datos
 * */

@Repository //Estereotipos
public interface PersonaRepository  extends JpaRepository<Persona,Long>{

}
