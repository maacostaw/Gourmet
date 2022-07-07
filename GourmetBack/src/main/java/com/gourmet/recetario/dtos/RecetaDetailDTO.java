package com.gourmet.recetario.dtos;

import java.util.ArrayList;
import java.util.List;

public class RecetaDetailDTO extends RecetaDTO{
	private List<IngredienteAbstractDTO> ingredientes = new ArrayList<>();

	public List<IngredienteAbstractDTO> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<IngredienteAbstractDTO> ingredientes) {
		this.ingredientes = ingredientes;
	}
}
