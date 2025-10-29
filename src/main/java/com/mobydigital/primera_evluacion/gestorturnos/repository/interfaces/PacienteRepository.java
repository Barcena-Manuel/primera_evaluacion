package com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces;

import java.util.List;

import com.mobydigital.primera_evluacion.gestorturnos.model.Paciente;


public interface PacienteRepository {
    public Paciente guardarPaciente(Paciente paciente);
    public Paciente obtenerPacientePorId(Long id);
    public Paciente eliminarPacientePorId(Long id);
    public List<Paciente> obtenerTodosLosPacientes();
    public Paciente actualizarPaciente(Long id, String nombre, String apellido, String dni, String email);
}
