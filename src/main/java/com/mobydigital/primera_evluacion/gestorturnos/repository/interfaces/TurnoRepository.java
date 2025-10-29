package com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.mobydigital.primera_evluacion.gestorturnos.model.Turno;

public interface TurnoRepository {
    public Turno registrarTurno(Turno turno);
    public List<Turno> listarTodosLosTurnos();
    public List<Turno> listarTurnosPorFecha(LocalDate hasta);
    public Turno eliminarTurnoPorId(Long id);
    public Turno obtenerTurnoPorId(Long id);
}
