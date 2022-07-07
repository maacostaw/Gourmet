package com.gourmet.recetario.enums;

public enum GrupoAlimenticio {
	LACTEOS("lácteos"), 
	CARNES("carnes"), 
	LEGUMBRES("legumbres"), 
	VEGETALES("vegetales"), 
	FRUTAS("frutas"), 
	CEREALES("cereales");
	
	private final String nombre;
	
	GrupoAlimenticio(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
}
