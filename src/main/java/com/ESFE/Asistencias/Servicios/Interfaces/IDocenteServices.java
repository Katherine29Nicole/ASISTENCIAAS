package com.ESFE.Asistencias.Servicios.Interfaces;

import com.ESFE.Asistencias.Entidades.Docente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IDocenteServices {
    Page<Docente> BuscarTodosPaginados(Pageable pageable);
    List<Docente> ObtenerTodos();
    Optional<Docente> BuscarporId(Integer id);
    Docente CreaOeditar(Docente grupo);
    void EliminarPorId(Integer id);
}

