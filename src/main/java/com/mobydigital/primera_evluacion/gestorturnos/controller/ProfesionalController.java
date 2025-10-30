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
        Profesional nuevo = service.crearProfesional(profesional);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Profesional>> obtenerTodasLasEspecialidades(){
        List<Profesional> profesionales = service.obtenerTodosLosProfesionales();
        return new ResponseEntity<>(profesionales, HttpStatus.OK);
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<Profesional>> obtenerProfesionalPorEspecialidad(@PathVariable(required = false) String especialidad) {
        List<Profesional> profesionales = service.obtenerProfesionalPorEspecialidad(especialidad);
        return new ResponseEntity<>(profesionales, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfesionalPorId(@PathVariable Long id){
        service.eliminarProfesionalPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
