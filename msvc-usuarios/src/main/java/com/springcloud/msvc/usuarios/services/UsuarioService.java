package com.springcloud.msvc.usuarios.services;

import com.springcloud.msvc.usuarios.models.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {

    List<UsuarioEntity> listarUsuarios();
    UsuarioEntity usuarioId(Long id);
    UsuarioEntity guardarUsuario(UsuarioEntity usuarioEntity);
    UsuarioEntity actualizarUsuario(UsuarioEntity usuarioEntity);
    void eliminarUsuario(Long idUsuario);

}
