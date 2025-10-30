package com.mobydigital.primera_evluacion.gestorturnos.repository.listaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mobydigital.primera_evluacion.gestorturnos.exception.DatoInvalidoException;
import com.mobydigital.primera_evluacion.gestorturnos.model.Turno;
import com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces.TurnoRepository;

@Component
public class TurnosListRepository implements TurnoRepository{

    private final Map<Long, Turno> turnos = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Turno registrarTurno(Turno turno) {
        if (turno.getId() == null) {
            turno.setId(nextId++);
        }

        turnos.put(turno.getId(), turno);
        return turno;
    }

    @Override
    public List<Turno> listarTodosLosTurnos() {
        return new ArrayList<>(turnos.values());
    }

    @Override
    public List<Turno> listarTurnosPorFecha(LocalDate hasta) {
        LocalDate hoy = LocalDate.now();

        return turnos.values().stream()
            .filter(turno ->
                (turno.getFecha().isEqual(hoy) || turno.getFecha().isAfter(hoy)) &&
                (turno.getFecha().isEqual(hasta) || turno.getFecha().isBefore(hasta))
            )
            .collect(Collectors.toList());
    }


    @Override
    public Turno obtenerTurnoPorId(Long id) throws DatoInvalidoException{
        Turno turno = turnos.get(id);
        if (turno == null) {
            throw new DatoInvalidoException("Turno no encontrado con ID: " + id);
        }

        return turno;
    }


    @Override
    public Turno eliminarTurnoPorId(Long id) {
        Turno turnoAEliminar = obtenerTurnoPorId(id);
        turnos.remove(id);
        return turnoAEliminar;
    }

}
