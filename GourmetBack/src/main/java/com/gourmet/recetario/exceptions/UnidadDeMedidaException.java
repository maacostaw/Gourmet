package com.gourmet.recetario.exceptions;

public class UnidadDeMedidaException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private final String unidadIngresada;
	private final String unidadEsperada;
	
	public UnidadDeMedidaException(String unidadIngresada, String unidadEsperada) {
		this.unidadIngresada = unidadIngresada;
		this.unidadEsperada = unidadEsperada;
	}
	
	@Override
	public String getMessage() {
		return "La unidad de medida ingresada (" + unidadIngresada + ") no corresponde con"
		+  "la unidad de medida especificada para este alimento (" + unidadEsperada + ").";
	}
}
