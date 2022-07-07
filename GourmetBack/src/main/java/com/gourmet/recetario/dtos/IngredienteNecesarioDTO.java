package com.gourmet.recetario.dtos;

public class IngredienteNecesarioDTO extends IngredienteAbstractDTO{
	private int medida;
	private String unidadDeMedida;
	public int getMedida() {
		return medida;
	}
	public void setMedida(int medida) {
		this.medida = medida;
	}
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
}
