package com.jhon.startup.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity /**Esto para que se mapee como una tabla como una base datos**/
@Table(name ="tbl_personas")
/**Esto para colocar el nombre de la tabla**/
/**Anotaciones LOMBOK*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @Id /**Esto indicas el PRIMARY KEY*/
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*Esto establece una estrategia*/
    private Long id;
    private String nombre;
    private int edad;

}
