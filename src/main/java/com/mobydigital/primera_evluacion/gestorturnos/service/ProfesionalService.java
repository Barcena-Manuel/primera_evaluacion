package com.mobydigital.primera_evluacion.gestorturnos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobydigital.primera_evluacion.gestorturnos.exception.DuplicadoException;
import com.mobydigital.primera_evluacion.gestorturnos.exception.RecursoNoEncontradoException;
import com.mobydigital.primera_evluacion.gestorturnos.model.Profesional;
import com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces.ProfesionalRepository;

@Service
public class ProfesionalService {

    @Autowired
    private ProfesionalRepository repository;

    public Profesional crearProfesional(Profesional profesional) throws DuplicadoException, RecursoNoEncontradoException{ 
        boolean duplicado = repository.obtenerTodosLosProfesionales().stream()
            .anyMatch(p -> p.getNombreCompleto().equalsIgnoreCase(profesional.getNombreCompleto()) &&
                           p.getEspecialidad().equalsIgnoreCase(profesional.getEspecialidad()));

        if(profesional.getNombreCompleto() == null || profesional.getEspecialidad() == null){
            throw new RecursoNoEncontradoException("No tiene datos cargados");
        }
        if (duplicado) {
            throw new DuplicadoException("Ya existe un profesional con ese nombre y especialidad");
        }

        return repository.guardarProfesional(profesional);
    }

    public Profesional obtenerProfesionalPorId(Long id) throws RecursoNoEncontradoException{
        if(id == null || id <= 0){
            throw new RecursoNoEncontradoException("El ID del profesional no es válido");
        }
        return repository.obtenerProfesionalPorId(id);
    }

    public List<Profesional> obtenerTodosLosProfesionales() throws RecursoNoEncontradoException{
        List<Profesional> profesionales = repository.obtenerTodosLosProfesionales();
        if(profesionales.isEmpty()){
            throw new RecursoNoEncontradoException("No se encontraron Profesionales");
        }
        return profesionales;
    }

    public List<Profesional> obtenerProfesionalPorEspecialidad(String especialidad) throws RecursoNoEncontradoException{
        List<Profesional> profesionales = obtenerTodosLosProfesionales();
        if(especialidad == null){
            throw new RecursoNoEncontradoException("No se encontro la Especialidad");
        }
        profesionales = profesionales.stream()
            .filter(p -> p.getEspecialidad().equalsIgnoreCase(especialidad)).toList();

        return profesionales;
    }

    public Profesional eliminarProfesionalPorId(Long id) throws RecursoNoEncontradoException{
        if(id == null || id <= 0){
            throw new RecursoNoEncontradoException("El ID del profesional no es válido");
        }
        return repository.eliminarProfesionalPorId(id);
    }
}
