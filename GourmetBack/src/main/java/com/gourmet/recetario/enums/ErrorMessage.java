package com.gourmet.recetario.enums;

public enum ErrorMessage {
	ALIMENTO_NO_ENCONTRADO("El alimento con el id dado no fue encontrado."),
	INGREDIENTE_NO_ENCONTRADO("El ingrediente con el id dado no fue encontrado."),
	RECETA_NO_ENCONTRADA("La receta con el id dado no fue encontrada."),
	RECETARIO_NO_ENCONTRADO("El recetario con el id dado no fue encontrado."),
	NO_RELACION_RECETA_INGREDIENTE("La receta no tiene asociado el ingrediente dado"),
	NO_RELACION_RECETARIO_RECETA("El recetario no tiene asociado la receta dada");

	
	private final String mensaje;
	
	ErrorMessage(String mensaje){
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}
