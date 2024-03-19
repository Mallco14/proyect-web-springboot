package com.jhon.startup.service;

import com.jhon.startup.entities.Persona;

import java.util.List;

public interface PersonaService {


    List<Persona> obtenerTodas();

    Persona obtnerPorId(Long id);

    Persona createPersona(Persona persona);

    Persona updatePersona(Long id, Persona persona);

    void deletePersona(Long id);

    long contarPersonas();

    List<Persona> obtenerTodasPersonas();

}
