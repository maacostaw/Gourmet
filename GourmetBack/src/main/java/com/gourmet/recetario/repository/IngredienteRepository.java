package com.gourmet.recetario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gourmet.recetario.entities.IngredienteAbstract;

@Repository("ingredienteRepository")
public interface IngredienteRepository extends JpaRepository<IngredienteAbstract,Long>{
	
}
