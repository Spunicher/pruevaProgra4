package com.alquiler.proyecto.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquiler.entidades.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{
public Usuario findByNick(String nick);
}
