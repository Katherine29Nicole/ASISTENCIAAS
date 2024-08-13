package com.ESFE.Asistencias.Repositorios;

import com.ESFE.Asistencias.Entidades.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocenteRepository extends JpaRepository<Docente, Integer> {
}
