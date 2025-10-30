package com.mobydigital.primera_evluacion.gestorturnos.model;


import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Component
public class Profesional {

    private Long id;
    
    private String nombreCompleto;

    private String especialidad;
}
