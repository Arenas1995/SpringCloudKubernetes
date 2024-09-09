package com.springcloud.msvc.cursos.clients;

import com.springcloud.msvc.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-usuarios", url = "localhost:8088/rest/v1.0/springcloud/")
public interface UsuarioClientRest {

    @GetMapping("/usuario/{idUsuario}")
    Usuario detalle(@PathVariable Long idUsuario);

    @PostMapping("/usuario/guardar")
    Usuario crear(@RequestBody Usuario usuario);
}
