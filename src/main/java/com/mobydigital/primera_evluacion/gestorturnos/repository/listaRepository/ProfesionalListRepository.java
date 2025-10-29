package com.mobydigital.primera_evluacion.gestorturnos.repository.listaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mobydigital.primera_evluacion.gestorturnos.exception.DatoInvalidoException;
import com.mobydigital.primera_evluacion.gestorturnos.model.Profesional;
import com.mobydigital.primera_evluacion.gestorturnos.repository.interfaces.ProfesionalRepository;

@Component
public class ProfesionalListRepository implements ProfesionalRepository {
    
    private final Map<Long, Profesional> profesionales = new HashMap<>();
    private Long nextId = 1L;

   @Override
    public Profesional guardarProfesional(Profesional profesional) {
        if(profesional.getId() == null){
            profesional.setId(nextId++);
        }

        profesionales.put(profesional.getId(), profesional);
        return profesional;
    }

    @Override
    public Profesional obtenerProfesionalPorId(Long id) throws DatoInvalidoException{
        Profesional profesional = profesionales.get(id);
        if(profesional != null) {
            return profesional;
        }else{
            throw new DatoInvalidoException("Profesional no encontrado con ID: " + id);
        }
    }

    @Override
    public Profesional eliminarProfesionalPorId(Long id) {
        Profesional profesionalAEliminar = obtenerProfesionalPorId(id);
        profesionales.remove(id);
        return profesionalAEliminar;
    }

    @Override
    public List<Profesional> obtenerTodosLosProfesionales() {
        List<Profesional> profesionalesList = new ArrayList<>(profesionales.values()); 
        return profesionalesList;
    }

}
