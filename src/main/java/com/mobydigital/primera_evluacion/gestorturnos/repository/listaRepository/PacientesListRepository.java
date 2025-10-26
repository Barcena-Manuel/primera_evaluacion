package com.mobydigital.primera_evluacion.gestorturnos.repository.listaRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mobydigital.primera_evluacion.gestorturnos.model.Paciente;
import com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces.PacienteRepository;

@Component
public class PacientesListRepository implements PacienteRepository {

    private List<Paciente> pacientes;

    public PacientesListRepository(){
        this.pacientes = new ArrayList<>();
    }

    @Override
    public Paciente crearPaciente(String nombre, String apellido, String dni, String email) {
        Long id = pacientes.size() + 1L;
        Paciente nuevoPaciente = new Paciente(id, nombre, apellido, dni, email);
        pacientes.add(nuevoPaciente);
        return nuevoPaciente;
    }

    @Override
    public Paciente obtenerPacientePorId(Long id) {
        Paciente pacienteEncontrado = pacientes.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if(pacienteEncontrado != null){
            return pacienteEncontrado;
        }else{
            throw new RuntimeException("Paciente no encontrado");   
        }
    }

    @Override
    public Paciente eliminarPacientePorId(Long id) {
        Paciente pacienteAEliminar = obtenerPacientePorId(id);
        if(pacienteAEliminar != null){
            pacientes.remove(pacienteAEliminar);
            return pacienteAEliminar;
        }else{
            throw new RuntimeException("Paciente no encontrado");
        }
    }

    @Override
    public List<Paciente> obtenerTodosLosPacientes() {
        if(pacientes.isEmpty()){
            throw new RuntimeException("No hay pacientes registrados");
        }else{
            return pacientes;
        }
    }

    @Override
    public Paciente actualizarPaciente(Long id, String nombre, String apellido, String dni, String email) {
        Paciente pacienteActualizar = obtenerPacientePorId(id);
        if(pacienteActualizar != null){
            pacienteActualizar.setNombre(nombre);
            pacienteActualizar.setApellido(apellido);
            pacienteActualizar.setDni(dni);
            pacienteActualizar.setEmail(email);
            return pacienteActualizar;
        }
        throw new UnsupportedOperationException("Unimplemented method 'actualizarPaciente'");
    }

}
