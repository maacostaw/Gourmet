package com.gourmet.recetario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gourmet.recetario.entities.Receta;

@Repository("recetaRepository")
public interface RecetaRepository extends JpaRepository<Receta, Long>{
	
}
