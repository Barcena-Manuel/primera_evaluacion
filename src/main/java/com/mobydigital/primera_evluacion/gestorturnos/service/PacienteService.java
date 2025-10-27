package com.mobydigital.primera_evluacion.gestorturnos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobydigital.primera_evluacion.gestorturnos.exception.DatoInvalidoException;
import com.mobydigital.primera_evluacion.gestorturnos.exception.DuplicadoException;
import com.mobydigital.primera_evluacion.gestorturnos.exception.RecursoNoEncontradoException;
import com.mobydigital.primera_evluacion.gestorturnos.model.Paciente;
import com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Paciente crearPaciente(Paciente paciente) throws DuplicadoException{
        boolean dniExistente = repository.obtenerTodosLosPacientes().stream()
            .anyMatch(p -> p.getDni().equals(paciente.getDni()));

        if (dniExistente) {
            throw new DuplicadoException("Ya existe un paciente con el DNI: " + paciente.getDni());
        }

        return repository.guardarPaciente(paciente);
    }


    public Paciente obtenerPacientePorId(Long id) throws RecursoNoEncontradoException{
        if(id == null || id <= 0){
            throw new RecursoNoEncontradoException("El id del paciente no es válido");
        }
        return repository.obtenerPacientePorId(id);
    }

    public List<Paciente> obtenerTodosLosPacientes(){
        List<Paciente> pacientes = repository.obtenerTodosLosPacientes();
        return pacientes;
    }

    public void eliminarPacientePorId(Long id) throws DatoInvalidoException{
        if(id == null || id <= 0){
            throw new DatoInvalidoException("El ID del paciente no es válido");
        }
        repository.eliminarPacientePorId(id);
    }
}
