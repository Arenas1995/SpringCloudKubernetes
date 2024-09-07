package com.springcloud.msvc.usuarios.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios", schema = "msvc_usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @Column(unique = true)
    private String email;
    private String password;
}
