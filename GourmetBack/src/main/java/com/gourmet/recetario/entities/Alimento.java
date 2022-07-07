package com.gourmet.recetario.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gourmet.recetario.enums.GrupoAlimenticio;
import com.gourmet.recetario.enums.GrupoAlimenticioConverter;
import com.gourmet.recetario.enums.UnidadDeMedida;
import com.gourmet.recetario.enums.UnidadDeMedidaConverter;

@Entity
@Table(name = "alimentos")
public class Alimento{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String nombre;
	private int calorias;
	@Convert(converter = UnidadDeMedidaConverter.class)
	private UnidadDeMedida unidadDeMedida;
	private int medidaDeReferencia;
	@Convert(converter = GrupoAlimenticioConverter.class)
	private GrupoAlimenticio grupoAlimenticio;
	
	/*
	 * Ejemplo con respecto al tema de las calorias:
	 * calorias = 50
	 * medidaDeReferencia = 100
	 * unidadDeMedida = "gr"
	 * eso significa en español
	 * 100 gramos tienen 50 calorias 
	 */
	
	public Alimento() {
		
	}
	
	/**
	 * Metodo constructor parametrizado
	 * @param nombre Nombre del alimento
	 * @param calorias Número de calorias para una medida de referencia
	 * @param medidaDeReferencia Medida de referencia asociada al número de calorias
	 * @param unidadDeMedida Tipo de unidad con la que se mide la masa del alimento
	 * @param grupoAlimenticio Grupo alimenticio del alimento
	 */
	public Alimento(String nombre, 
			int calorias, 
			int medidaDeReferencia, 
			UnidadDeMedida unidadDeMedida,
			GrupoAlimenticio grupoAlimenticio) {
		this.nombre = nombre;
		this.calorias = calorias;
		this.medidaDeReferencia = medidaDeReferencia;
		this.unidadDeMedida = unidadDeMedida;
		this.grupoAlimenticio = grupoAlimenticio;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public UnidadDeMedida getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	public int getMedidaDeReferencia() {
		return medidaDeReferencia;
	}

	public void setMedidaDeReferencia(int medidaDeReferencia) {
		this.medidaDeReferencia = medidaDeReferencia;
	}

	public GrupoAlimenticio getGrupoAlimenticio() {
		return grupoAlimenticio;
	}

	public void setGrupoAlimenticio(GrupoAlimenticio grupoAlimenticio) {
		this.grupoAlimenticio = grupoAlimenticio;
	}

	@Override
	public String toString() {
		return this.nombre + " (medido en " + this.unidadDeMedida.getNombre() + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		} if(obj == this) {
			return true;
		}
		return this.nombre.equals(((Alimento) obj).getNombre());
	}
}