package com.gourmet.recetario.dtos;

public class IngredienteAbstractDTO {
	private Long id;
	private AlimentoDTO alimento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AlimentoDTO getAlimento() {
		return alimento;
	}
	public void setAlimento(AlimentoDTO alimento) {
		this.alimento = alimento;
	}

	
}
