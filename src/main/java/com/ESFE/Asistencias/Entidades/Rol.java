package com.ESFE.Asistencias.Entidades;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import javax.annotation.processing.Generated;

@Entity
@Table(name="roles")
public class Rol {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nombre es requerido")
    private String nombre;

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

     public @NotBlank(message= "Nombre es requerido") String getNombre() {return nombre;}

    public void setNombre(@NotBlank(message = "Nombre es requerido") String nombre) {this.nombre = nombre;}
}
