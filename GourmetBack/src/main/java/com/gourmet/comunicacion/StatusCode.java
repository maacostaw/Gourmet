package com.gourmet.comunicacion;

public enum StatusCode {
	CODE200("200 Ok"),
	CODE202("202 Accepted"),
	CODE405("405 Method Not Allowed");
	
	private final String texto;
	
	StatusCode(String texto){
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}
}
