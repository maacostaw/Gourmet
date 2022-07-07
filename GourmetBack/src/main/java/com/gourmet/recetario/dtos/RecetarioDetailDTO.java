package com.gourmet.recetario.dtos;

import java.util.ArrayList;
import java.util.List;

public class RecetarioDetailDTO extends RecetarioDTO{
	private List<RecetaDTO> recetas = new ArrayList<>();

	public List<RecetaDTO> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<RecetaDTO> recetas) {
		this.recetas = recetas;
	}
}
