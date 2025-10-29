package com.mobydigital.primera_evluacion.gestorturnos.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobydigital.primera_evluacion.gestorturnos.exception.DatoInvalidoException;
import com.mobydigital.primera_evluacion.gestorturnos.exception.DuplicadoException;
import com.mobydigital.primera_evluacion.gestorturnos.exception.RecursoNoEncontradoException;
import com.mobydigital.primera_evluacion.gestorturnos.model.Turno;
import com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces.TurnoRepository;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository repository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ProfesionalService profesionalService;

    public Turno crearTurno(Turno turno) throws DuplicadoException{
        pacienteService.obtenerPacientePorId(turno.getPaciente().getId());
        profesionalService.obtenerProfesionalPorId(turno.getProfesional().getId());

        boolean existeDuplicado = repository.listarTodosLosTurnos().stream()
                .anyMatch(t -> t.getPaciente().getId().equals(turno.getPaciente().getId()) &&
                               t.getProfesional().getId().equals(turno.getProfesional().getId()) &&
                               t.getFecha().equals(turno.getFecha()));

        if (existeDuplicado) {
            throw new DuplicadoException("Ya existe un turno para ese paciente, profesional y fecha");
        }
        
        return repository.registrarTurno(turno);
    }

    public List<Turno> obtenerTodosLosTurnos() throws RecursoNoEncontradoException{
        List<Turno> turnos = repository.listarTodosLosTurnos();
        if(turnos.isEmpty()){
            throw new RecursoNoEncontradoException("No se encontraron Turnos");
        }
        return turnos;
    }

    public List<Turno> obtenerTurnosPorFecha(LocalDate hasta) throws RecursoNoEncontradoException{
        List<Turno> turnos = repository.listarTurnosPorFecha(hasta);
        if(turnos.isEmpty()){
            throw new RecursoNoEncontradoException("No se encontraron Turnos");
        }
        return turnos;
    }

    public Turno obtenerTurnoPorId(Long id) throws RecursoNoEncontradoException{
        if(id == null || id <= 0){
            throw new RecursoNoEncontradoException("El ID del turno no es válido");
        }
        return repository.obtenerTurnoPorId(id);
    }

    public Turno eliminarTurnoPorId(Long id) throws DatoInvalidoException{
        if(id == null || id <= 0){
            throw new DatoInvalidoException("El ID del turno no es válido");
        }
        return repository.eliminarTurnoPorId(id);
    }
}
