package com.springcloud.msvc.usuarios.controllers;

import com.springcloud.msvc.usuarios.models.entity.UsuarioEntity;
import com.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar-usuarios")
    public ResponseEntity<?> listarUsuarios() {
        return ResponseEntity.ok().body(usuarioService.listarUsuarios());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> buscarUsuario(@PathVariable("idUsuario") Long idUsuario) {
        return ResponseEntity.ok().body(usuarioService.usuarioId(idUsuario));
    }

    @PostMapping("/guardar-usuario")
    public ResponseEntity<?> guardarUsuarios(@RequestBody UsuarioEntity usuarioEntity) {
        return ResponseEntity.ok().body(usuarioService.guardarUsuario(usuarioEntity));
    }

    @PutMapping("/actualizar-usuario")
    public ResponseEntity<?> actualizarUsuarios(@RequestBody UsuarioEntity usuarioEntity) {
        return ResponseEntity.ok().body(usuarioService.actualizarUsuario(usuarioEntity));
    }

    @DeleteMapping("/eliminar-usuario/{idUsuario}")
    public ResponseEntity<?> eliminarUsuarios(@PathVariable("idUsuario") Long idUsuario) {
        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.ok().body(null);
    }

}
