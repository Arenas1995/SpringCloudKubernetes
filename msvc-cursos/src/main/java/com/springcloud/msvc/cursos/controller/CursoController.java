package com.springcloud.msvc.cursos.controller;

import com.springcloud.msvc.cursos.models.Usuario;
import com.springcloud.msvc.cursos.models.entity.Curso;
import com.springcloud.msvc.cursos.services.CursoService;
import feign.FeignException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {

        Optional<Curso> optionalCurso = cursoService.findById(id);

        return optionalCurso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Curso> guardar(@Valid @RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Curso> actualizar(@Valid @RequestBody Curso curso, @PathVariable Long id) {

        Optional<Curso> optionalCurso = cursoService.findById(id);

        if (optionalCurso.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Curso> eliminar(@PathVariable Long id) {

        Optional<Curso> optionalCurso = cursoService.findById(id);

        if (optionalCurso.isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<Optional<Usuario>> asignarUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Long cursoId) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.asignarUsuario(usuario, cursoId));
        } catch (FeignException ex) {
            System.out.println(ex);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/crear-usuario/{cursoId}")
    public ResponseEntity<Optional<Usuario>> crearUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Long cursoId) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.crearUsuario(usuario, cursoId));
        } catch (FeignException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/eliminar-usuario/{cursoId}")
    public ResponseEntity<Optional<Usuario>> eliminarUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Long cursoId) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(cursoService.eliminarUsuario(usuario, cursoId));
        } catch (FeignException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
