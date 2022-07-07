package com.gourmet.recetario.entities;

import javax.persistence.Entity;

import com.gourmet.recetario.enums.UnidadDeMedida;

@Entity
public class IngredienteOpcional extends IngredienteAbstract{
	
	public IngredienteOpcional() {
		
	}
	
	/**
	 * Consructor para un ingrediente cuyo uso es opcional
	 * @param alimento Alimento del ingrediente
	 */
	public IngredienteOpcional(Alimento alimento) {
		super(alimento);
	}

	public int calcularCalorias() {
		return 0;
	}
	
	@Override
	public String toString() {
		return this.alimento.getNombre() + " " + UnidadDeMedida.CANTIDADNECESARIA.getNombre();
	}
}