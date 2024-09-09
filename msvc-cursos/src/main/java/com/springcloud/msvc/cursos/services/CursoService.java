package com.springcloud.msvc.cursos.services;

import com.springcloud.msvc.cursos.models.Usuario;
import com.springcloud.msvc.cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> listar();
    Optional<Curso> findById(Long id);
    Curso guardar(Curso curso);
    Curso actualizar(Curso curso);
    void eliminar(Long id);

    Optional<Usuario> asignarUsuario(Usuario usuario, Long id);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);
}
