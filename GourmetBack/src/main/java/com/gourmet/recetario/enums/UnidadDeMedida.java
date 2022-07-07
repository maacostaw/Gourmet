package com.gourmet.recetario.enums;

public enum UnidadDeMedida {
	GRAMOS("gr"),
	UNIDAD("unidad"),
	CANTIDADNECESARIA("c/n");
	
	private final String nombre;
	
	UnidadDeMedida(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}
