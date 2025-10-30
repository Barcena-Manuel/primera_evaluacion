package com.mobydigital.primera_evluacion.gestorturnos.model;

import java.time.LocalDate;

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
public class Turno{

    private Long id;

    private Paciente paciente;

    private Profesional profesional;

    LocalDate fecha;
}
