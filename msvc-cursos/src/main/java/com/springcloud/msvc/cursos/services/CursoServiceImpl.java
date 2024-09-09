package com.springcloud.msvc.cursos.services;

import com.springcloud.msvc.cursos.clients.UsuarioClientRest;
import com.springcloud.msvc.cursos.models.Usuario;
import com.springcloud.msvc.cursos.models.entity.Curso;
import com.springcloud.msvc.cursos.models.entity.CursoUsuario;
import com.springcloud.msvc.cursos.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    private final UsuarioClientRest usuarioClientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Curso actualizar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long id) {

        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            Usuario usuarioMsvc = usuarioClientRest.detalle(usuario.getId());

            Curso cursoMsvc = curso.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());

            cursoMsvc.addCursoUsuario(cursoUsuario);
            cursoRepository.save(cursoMsvc);

            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> curso = cursoRepository.findById(cursoId);
        if (curso.isPresent()) {
            Usuario usuarioNuevoMsvc = usuarioClientRest.crear(usuario);

            Curso cursoMsvc = curso.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioNuevoMsvc.getId());

            cursoMsvc.addCursoUsuario(cursoUsuario);
            cursoRepository.save(cursoMsvc);

            return Optional.of(usuarioNuevoMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {

        Optional<Curso> curso = cursoRepository.findById(cursoId);
        if (curso.isPresent()) {
            Usuario usuarioMsvc = usuarioClientRest.detalle(usuario.getId());

            Curso cursoMsvc = curso.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());

            cursoMsvc.deleteCursoUsuario(cursoUsuario);
            cursoRepository.save(cursoMsvc);

            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }
}
