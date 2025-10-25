package com.mobydigital.primera_evluacion.gestorturnos.repository.listaRepository;

import java.util.ArrayList;
import java.util.List;

import com.mobydigital.primera_evluacion.gestorturnos.model.Profesional;
import com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces.ProfesionalRepository;

public class ProfesionalListRepository implements ProfesionalRepository {
    
    private List<Profesional> profesionalas;

    public ProfesionalListRepository() {
        this.profesionalas = new ArrayList<>();
    }

    @Override
    public Profesional crearProfesional(String nombreCompleto, String especialidad) {
        Long id = profesionalas.size() + 1L;
        Profesional nuevoProfesional = new Profesional(id, nombreCompleto, especialidad);
        profesionalas.add(nuevoProfesional);
        return nuevoProfesional;
    }

    @Override
    public Profesional obtenerProfesionalPorId(Long id) {
        Profesional profesionalEncontrado = profesionalas.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if(profesionalEncontrado != null) {
            return profesionalEncontrado;
        }else{
            throw new RuntimeException("Profesional no encontrado con ID: " + id);
        }
    }

    @Override
    public Profesional eliminarProfesionalPorId(Long id) {
        Profesional profesionalAEliminar = obtenerProfesionalPorId(id);
        if(profesionalAEliminar != null) {
            profesionalas.remove(profesionalAEliminar);
            return profesionalAEliminar;
        }else{
            throw new RuntimeException("Profesional no encontrado con ID: " + id);
        }
    }

    @Override
    public List<Profesional> obtenerTodosLosProfesionales() {
        if(profesionalas.isEmpty()) {
            throw new RuntimeException("No hay profesionales registrados.");
        }
        
        return profesionalas;
    }

}
