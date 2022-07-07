package com.gourmet.recetario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gourmet.recetario.entities.Alimento;

@Repository("alimentoRepository")
public interface AlimentoRepository extends JpaRepository<Alimento,Long> {
	
}
