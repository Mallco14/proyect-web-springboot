package com.jhon.startup.service.impl;

import com.jhon.startup.entities.Persona;
import com.jhon.startup.repository.PersonaRepository;
import com.jhon.startup.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> obtenerTodas() {
        return personaRepository.findAll();
    }
    @Override
    public Persona obtnerPorId(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override
    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }
    @Override
    public Persona updatePersona(Long id, Persona persona) {
        Persona personaBD = personaRepository.findById(id).orElse(null);
        if(personaBD != null){
            personaBD.setNombre(persona.getNombre());
            personaBD.setEdad(persona.getEdad());
            return personaRepository.save(personaBD);
        }
        return null;
    }

    @Override
    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public long contarPersonas() {
        return personaRepository.count();
    }

    @Override
    public List<Persona> obtenerTodasPersonas() {
        return personaRepository.findAll();
    }
}
