package com.jhon.startup.controller;


import com.jhon.startup.entities.Persona;
import com.jhon.startup.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    //La clase MODEL se usa para transferir objetos de controller a la vista.
    @GetMapping
    public String listarPersonas(Model model){
        List<Persona> personas = personaService.obtenerTodasPersonas();
        model.addAttribute("listaPersona",personas);//key-valor
        return "listar";
    }
/**
 *Crear una nueva persona
 * */
    @GetMapping("/nueva")
    public String mostrarFormularioDeNuevaPersona(Model model){
        model.addAttribute("persona",new Persona());
        model.addAttribute("accion","/personas/nueva");
        return "formulario";
    }
    @PostMapping("/nueva")
    public String guardarNuevaPersona(@ModelAttribute Persona persona){/***/
        personaService.createPersona(persona);
        /*PARA VER SI SE GUARDARON*/
        return "redirect:/personas";
    }

    /**
     *Actualizar una nueva persona
     * */
    @GetMapping("/editar/{id}")
    public String mostrarPersonaFormularioEditarPersona(@PathVariable Long id,
                                                        Model model){
        Persona persona = personaService.obtnerPorId(id);
        model.addAttribute("persona",persona);
        model.addAttribute("accion","/personas/editar/"+id);
       return "formulario";
    }

    @PostMapping("/editar/{id}")
    public  String actualizarPersona(@PathVariable Long id,@ModelAttribute Persona persona){
        personaService.updatePersona(id,persona);
        return "redirect:/personas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id){
        personaService.deletePersona(id);
        return "redirect:/personas";

    }

}
