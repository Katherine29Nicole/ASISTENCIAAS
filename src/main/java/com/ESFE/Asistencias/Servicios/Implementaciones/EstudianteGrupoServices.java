package com.ESFE.Asistencias.Servicios.Implementaciones;

import com.ESFE.Asistencias.Entidades.EstudianteGrupo;
import com.ESFE.Asistencias.Repositorios.IEstudianteGrupoRepository;
import com.ESFE.Asistencias.Servicios.Interfaces.IEstudianteGrupoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EstudianteGrupoServices implements IEstudianteGrupoServices {

    @Autowired
    private IEstudianteGrupoRepository iEstudianteGrupoRepository;

    @Override
    public Page<EstudianteGrupo> BuscarTodosPaginados(Pageable pageable) {
        return iEstudianteGrupoRepository.findAll(pageable);
    }


    @Override
    public List<EstudianteGrupo> ObtenerTodos() {
        return iEstudianteGrupoRepository.findAll();
    }

    @Override
    public Optional<EstudianteGrupo> BuscarporId(Integer id) {
        return iEstudianteGrupoRepository.findById(id);
    }

    @Override
    public EstudianteGrupo CreaOeditar(EstudianteGrupo estudianteGrupo) {
        return iEstudianteGrupoRepository.save(estudianteGrupo);
    }

    @Override
    public void EliminarPorId(Integer id) {
        iEstudianteGrupoRepository.deleteById(id);
    }



}
