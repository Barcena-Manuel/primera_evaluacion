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

    public Profesional crearProfesional(Profesional profesional) throws DuplicadoException{ 
        boolean duplicado = repository.obtenerTodosLosProfesionales().stream()
            .anyMatch(p -> p.getNombreCompleto().equalsIgnoreCase(profesional.getNombreCompleto()) &&
                           p.getEspecialidad().equalsIgnoreCase(profesional.getEspecialidad()));

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

    public List<Profesional> obtenerTodosLosProfesionales(){
        return repository.obtenerTodosLosProfesionales();
    }

    public void eliminarProfesionalPorId(Long id) throws RecursoNoEncontradoException{
        if(id == null || id <= 0){
            throw new RecursoNoEncontradoException("El ID del profesional no es válido");
        }
        repository.eliminarProfesionalPorId(id);
    }
}
