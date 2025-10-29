package com.mobydigital.primera_evluacion.gestorturnos.runner;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mobydigital.primera_evluacion.gestorturnos.model.Paciente;
import com.mobydigital.primera_evluacion.gestorturnos.model.Profesional;
import com.mobydigital.primera_evluacion.gestorturnos.model.Turno;
import com.mobydigital.primera_evluacion.gestorturnos.service.PacienteService;
import com.mobydigital.primera_evluacion.gestorturnos.service.ProfesionalService;
import com.mobydigital.primera_evluacion.gestorturnos.service.TurnoService;

@Component
public class TestCargaInicial implements CommandLineRunner {

    private final PacienteService pacienteService;

    private final ProfesionalService profesionalService;

    private final TurnoService turnoService;

    public TestCargaInicial(PacienteService paciente, ProfesionalService profesional, TurnoService turno){
        this.pacienteService = paciente;
        this.profesionalService = profesional;
        this.turnoService = turno;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Cargando datos iniciales...");

        // Creando pacientes de prueba
        Paciente p1 = new Paciente(null, "Juan", "Pérez", "12345678", "juan@mail.com");
        Paciente p2 = new Paciente(null, "Ana", "García", "87654321", "ana@mail.com");
        pacienteService.crearPaciente(p1);
        pacienteService.crearPaciente(p2);

        // Creando profesionales de prueba
        Profesional prof1 = new Profesional(null, "Dr. López", "Clinica");
        Profesional prof2 = new Profesional(null, "Dra. Suárez", "Odontologia");
        profesionalService.crearProfesional(prof1);
        profesionalService.crearProfesional(prof2);

        // Creando turnos de prueba
        turnoService.crearTurno(new Turno(null, p1, prof1, LocalDate.now().plusDays(1)));
        turnoService.crearTurno(new Turno(null, p2, prof2, LocalDate.now().plusDays(2)));
        turnoService.crearTurno(new Turno(null, p1, prof2, LocalDate.now().plusDays(3)));
        turnoService.crearTurno(new Turno(null, p2, prof1, LocalDate.now().plusDays(4)));


        System.out.println("Datos cargados correctamente:");
        System.out.println("Pacientes: " + pacienteService.obtenerTodosLosPacientes().size());
        System.out.println("Profesionales: " + profesionalService.obtenerTodosLosProfesionales().size());
        System.out.println("Turnos: " + turnoService.obtenerTodosLosTurnos().size());
    }
}
