package com.mobydigital.primera_evluacion.gestorturnos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobydigital.primera_evluacion.gestorturnos.model.Paciente;
import com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Paciente crearPaciente(String nombre, String apellido, String dni, String email) {
        List<Paciente> pacientes = repository.obtenerTodosLosPacientes();
        pacientes.stream().forEach(p -> {
            if(p.getDni().equals(dni)){
                throw new RuntimeException("Ya existe un paciente con el DNI: " + dni);
            }
        });
        return repository.crearPaciente(nombre, apellido, dni, email);
    }

    public Paciente obtenerPacientePorId(Long id) {
        if(id == null || id <= 0){
            throw new RuntimeException("No existe un paciente con ese id");
        }
        return repository.obtenerPacientePorId(id);
    }

    public List<Paciente> obtenerTodosLosPacientes(){
        List<Paciente> pacientes = repository.obtenerTodosLosPacientes();
        if(pacientes.isEmpty()){
            throw new RuntimeException("No hay pacientes registrados"); 
        }
        return pacientes;
    }

    public void eliminarPacientePorId(Long id){
        Paciente paciente = obtenerPacientePorId(id);
        if(paciente == null){
            throw new RuntimeException("No existe un paciente con ese id");
        }
        repository.eliminarPacientePorId(id);
    }
}
