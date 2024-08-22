package com.ESFE.Asistencias.Servicios.Interfaces;


import com.ESFE.Asistencias.Entidades.DocenteGrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IDocenteGrupoServices {
    Page<DocenteGrupo> BuscarTodosPaginados(Pageable pageable);
    List<DocenteGrupo> ObtenerTodos();
    Optional<DocenteGrupo> BuscarporId(Integer id);
    DocenteGrupo CreaOeditar(DocenteGrupo Docentegrupo);
    void EliminarPorId(Integer id);
}
