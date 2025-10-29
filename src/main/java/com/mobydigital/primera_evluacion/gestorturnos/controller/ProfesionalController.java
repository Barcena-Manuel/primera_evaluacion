package com.mobydigital.primera_evluacion.gestorturnos.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobydigital.primera_evluacion.gestorturnos.model.Profesional;
import com.mobydigital.primera_evluacion.gestorturnos.service.ProfesionalService;

@RestController
@RequestMapping("/profesionales")
public class ProfesionalController {
    
    @Autowired
    private ProfesionalService service;

    @PostMapping
    public ResponseEntity<Profesional> crearProfesional(@RequestBody Profesional profesional) {
        try {
            Profesional nuevo = service.crearProfesional(profesional);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<List<Profesional>> obtenerProfesionalPorEspecialidad(@RequestParam(required = false) String especialidad) {
        List<Profesional> profesionales = service.obtenerTodosLosProfesionales();

        if (especialidad != null) {
            profesionales = profesionales.stream()
                .filter(p -> p.getEspecialidad().equalsIgnoreCase(especialidad))
                .toList();
        }

        return new ResponseEntity<>(profesionales, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profesional> eliminarProfesionalPorId(@PathVariable Long id){
        try {
            Profesional profesional = service.eliminarProfesionalPorId(id);
            return new ResponseEntity<>(profesional, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
