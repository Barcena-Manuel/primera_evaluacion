package com.mobydigital.primera_evluacion.gestorturnos.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobydigital.primera_evluacion.gestorturnos.model.Turno;
import com.mobydigital.primera_evluacion.gestorturnos.service.TurnoService;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService service;

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) {
        try {
            Turno nuevoTurno = service.crearTurno(turno);
            return new ResponseEntity<>(nuevoTurno, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos() {
        List<Turno> turnos = service.obtenerTodosLosTurnos();
        if (turnos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(turnos, HttpStatus.OK);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Turno>> listarTurnosPorFecha(@PathVariable String fecha) {
        LocalDate fechaParam = LocalDate.parse(fecha);
        List<Turno> turnos = service.obtenerTurnosPorFecha(fechaParam);
        if (turnos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(turnos, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        try {
            service.eliminarTurnoPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
