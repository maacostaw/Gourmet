package com.gourmet.recetario.dtos;

public class AlimentoDTO {
	private long id;
	private String nombre;
	private int calorias;
	private String unidadDeMedida;
	private int medidaDeReferencia;
	private String grupoAlimenticio;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCalorias() {
		return calorias;
	}
	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	public int getMedidaDeReferencia() {
		return medidaDeReferencia;
	}
	public void setMedidaDeReferencia(int medidaDeReferencia) {
		this.medidaDeReferencia = medidaDeReferencia;
	}
	public String getGrupoAlimenticio() {
		return grupoAlimenticio;
	}
	public void setGrupoAlimenticio(String grupoAlimenticio) {
		this.grupoAlimenticio = grupoAlimenticio;
	}
}
