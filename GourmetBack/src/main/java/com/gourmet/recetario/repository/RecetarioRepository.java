package com.gourmet.recetario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gourmet.recetario.entities.Recetario;

@Repository("recetarioRepository")
public interface RecetarioRepository extends JpaRepository<Recetario, Long>{

}
