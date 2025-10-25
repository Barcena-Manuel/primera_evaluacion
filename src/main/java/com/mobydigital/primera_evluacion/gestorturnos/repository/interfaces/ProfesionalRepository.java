package com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces;

import java.util.List;

import com.mobydigital.primera_evluacion.gestorturnos.model.Profesional;

public interface ProfesionalRepository {
    public Profesional crearProfesional(String nombreCompleto, String especialidad);
    public Profesional obtenerProfesionalPorId(Long id);
    public Profesional eliminarProfesionalPorId(Long id);
    public List<Profesional> obtenerTodosLosProfesionales();
}
