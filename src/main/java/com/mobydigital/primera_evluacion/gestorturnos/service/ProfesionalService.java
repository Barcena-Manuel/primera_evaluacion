package com.mobydigital.primera_evluacion.gestorturnos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobydigital.primera_evluacion.gestorturnos.model.Profesional;
import com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces.ProfesionalRepository;

@Service
public class ProfesionalService {

    @Autowired
    private ProfesionalRepository repository;

    public Profesional crearProfesional(String nombreCompleto, String especialidad) {
        List<Profesional> profesionales = repository.obtenerTodosLosProfesionales();
        boolean existe = profesionales.stream().anyMatch(p ->
            p.getNombreCompleto().equals(nombreCompleto) && p.getEspecialidad().equals(especialidad));
        if(existe){
            throw new IllegalArgumentException("El profesional ya está registrado");
        }
        return repository.crearProfesional(nombreCompleto, especialidad);
    }

    public Profesional obtenerProfesionalPorId(Long id){
        if(id == null || id <= 0){
            throw new IllegalArgumentException("El ID del profesional no es válido");
        }
        return repository.obtenerProfesionalPorId(id);
    }

    public List<Profesional> obtenerTodosLosProfesionales(){
        List<Profesional> profesionales = repository.obtenerTodosLosProfesionales();
        if(profesionales.isEmpty()){
            throw new IllegalArgumentException("No hay profesionales registrados");
        }
        return repository.obtenerTodosLosProfesionales();
    }

    public void eliminarPacientePorId(Long id){
        Profesional profesional = obtenerProfesionalPorId(id);
        if(profesional == null){
            throw new IllegalArgumentException("No se encontró un profesional con el ID proporcionado");
        }
        repository.eliminarProfesionalPorId(id);
    }
}
