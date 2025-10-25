package com.mobydigital.primera_evluacion.gestorturnos.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Component
public class Paciente {

    private Long id;

    private String nombre;

    private String apellido;

    private String dni;
    
    private String email;

}
