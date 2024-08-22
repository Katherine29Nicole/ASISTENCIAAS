package com.ESFE.Asistencias.Servicios.Interfaces;

import com.ESFE.Asistencias.Entidades.EstudianteGrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEstudianteGrupoServices {
    Page<EstudianteGrupo> BuscarTodosPaginados(Pageable pageable);
    List<EstudianteGrupo> ObtenerTodos();
    Optional<EstudianteGrupo> BuscarporId(Integer id);
    EstudianteGrupo CreaOeditar(EstudianteGrupo estudianteGrupo);
    void EliminarPorId(Integer id);
}
