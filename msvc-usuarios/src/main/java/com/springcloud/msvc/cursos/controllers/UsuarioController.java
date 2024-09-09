package com.springcloud.msvc.cursos.controllers;

import com.springcloud.msvc.cursos.models.entity.UsuarioEntity;
import com.springcloud.msvc.cursos.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios() {
        return ResponseEntity.ok().body(usuarioService.listarUsuarios());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> buscarUsuario(@PathVariable("idUsuario") Long idUsuario) {
        return ResponseEntity.ok().body(usuarioService.usuarioId(idUsuario));
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarUsuarios(@Valid @RequestBody UsuarioEntity usuarioEntity) {
        return ResponseEntity.ok().body(usuarioService.guardarUsuario(usuarioEntity));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarUsuarios(@Valid @RequestBody UsuarioEntity usuarioEntity) {
        return ResponseEntity.ok().body(usuarioService.actualizarUsuario(usuarioEntity));
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<?> eliminarUsuarios(@PathVariable("idUsuario") Long idUsuario) {
        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.ok().body(null);
    }

}
