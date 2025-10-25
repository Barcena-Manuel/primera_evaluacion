package com.mobydigital.primera_evluacion.gestorturnos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobydigital.primera_evluacion.gestorturnos.model.Profesional;
import com.mobydigital.primera_evluacion.gestorturnos.service.ProfesionalService;

@RestController
@RequestMapping("/profesionales")
public class ProfesionalController {
    
    @Autowired
    private ProfesionalService service;

    public ResponseEntity<Profesional> crearProfesional(@RequestBody Profesional profesional) {
        Profesional nuevoProfesional = service.crearProfesional(profesional.getNombreCompleto(), profesional.getEspecialidad());
        if(nuevoProfesional == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(nuevoProfesional, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Profesional>> obtenerProfesionalPorEspecialidad(@RequestParam(required = false) String especialidad){
        List<Profesional> profesionales = service.obtenerTodosLosProfesionales();
        List<Profesional> profesionalFilatrado = profesionales.stream().filter(p ->especialidad != null && p.getEspecialidad().equals(especialidad)).toList();
        if(profesionalFilatrado == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profesionalFilatrado, HttpStatus.OK);
    }
}
