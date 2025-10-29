package com.mobydigital.primera_evluacion.gestorturnos.repository.listaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mobydigital.primera_evluacion.gestorturnos.exception.DatoInvalidoException;
import com.mobydigital.primera_evluacion.gestorturnos.model.Paciente;
import com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces.PacienteRepository;

@Component
public class PacientesListRepository implements PacienteRepository {

    private final Map<Long, Paciente> pacientes = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        if(paciente.getId() == null){
            paciente.setId(nextId++);
        }

        pacientes.put(paciente.getId(), paciente);
        return paciente;
    }

    @Override
    public Paciente obtenerPacientePorId(Long id) throws DatoInvalidoException{
        Paciente paciente = pacientes.get(id);
        if (paciente != null) {
            return paciente;
        } else {
            throw new DatoInvalidoException("Paciente no encontrado");
        }
    }


    @Override
    public Paciente eliminarPacientePorId(Long id) {
        Paciente pacienteAEliminar = obtenerPacientePorId(id);
        pacientes.remove(id);
        return pacienteAEliminar;
    }

    @Override
    public List<Paciente> obtenerTodosLosPacientes() {
        List<Paciente> pacientesList = new ArrayList<>(pacientes.values());
        return pacientesList;
    }


    @Override
    public Paciente actualizarPaciente(Long id, String nombre, String apellido, String dni, String email) {
        Paciente pacienteActualizar = obtenerPacientePorId(id);
        pacienteActualizar.setNombre(nombre);
        pacienteActualizar.setApellido(apellido);
        pacienteActualizar.setDni(dni);
        pacienteActualizar.setEmail(email);
        return pacienteActualizar;
    }


}
