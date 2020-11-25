package com.alquiler.proyecto.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.entidades.alquiler;



@Repository
public interface IAlquiler extends JpaRepository<alquiler, Integer>{

}
