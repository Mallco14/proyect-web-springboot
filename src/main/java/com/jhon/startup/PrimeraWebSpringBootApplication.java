package com.jhon.startup;

import com.jhon.startup.entities.Persona;
import com.jhon.startup.repository.PersonaRepository;
import com.jhon.startup.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PrimeraWebSpringBootApplication{

	/**Recordemos inyecto esto porque depende de otro*/
	@Autowired
	private PersonaService personaService;

	public static void main(String[] args) {
		SpringApplication.run(PrimeraWebSpringBootApplication.class, args);

	}


	/**@Override
	public void run(String... args) throws Exception {
		//Guardando datos en la BASE DE DATOS
		personaService.createPersona(new Persona(5L,"Jose",22));
		personaService.createPersona(new Persona(6L,"Dionisio",12));
		personaService.createPersona(new Persona(7L,"Annet",42));
		personaService.createPersona(new Persona(8L,"Danna",40));

		//Mostramos numero de personas
		System.out.println("Numero de personas de la tabla : "+ personaService.contarPersonas());

		//Mostramos la lista de personas
		List<Persona> persona = personaService.obtenerTodasPersonas();
		persona.forEach(p -> System.out.println("Nombre de la persona : "+ p.getNombre()));


	}**/
}
