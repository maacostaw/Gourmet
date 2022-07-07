package com.gourmet.recetario.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

import com.gourmet.recetario.enums.UnidadDeMedida;
import com.gourmet.recetario.enums.UnidadDeMedidaConverter;
import com.gourmet.recetario.exceptions.UnidadDeMedidaException;

@Entity
public class IngredienteNecesario extends IngredienteAbstract{
	@Column(name="medida")
	private int medida;
	@Convert(converter = UnidadDeMedidaConverter.class)
	private UnidadDeMedida unidadDeMedida; 
	
	public IngredienteNecesario() {
		super();
	}
		
	/**
	 * Consructor para un ingrediente cuyo uso es necesario
	 * @param medida Cantidad necesaria del ingrediente
	 * @param unidadDeMedida Tipo de unidad con la que se mide la masa del alimento
	 * @param alimento Alimento del ingrediente
	 */
	public IngredienteNecesario(int medida, UnidadDeMedida unidadDeMedida, Alimento alimento) throws UnidadDeMedidaException{
		super(alimento);
		if(alimento.getUnidadDeMedida() != unidadDeMedida) {
			String unidadIngresada = unidadDeMedida.getNombre();
			String unidadEsperada = alimento.getUnidadDeMedida().getNombre();
			throw new UnidadDeMedidaException(unidadIngresada,unidadEsperada);
		}
		this.medida = medida;
		this.unidadDeMedida = unidadDeMedida;
	}
	
	public int getMedida() {
		return medida;
	}

	public void setMedida(int medida) {
		this.medida = medida;
	}

	public UnidadDeMedida getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}


	public int calcularCalorias() {
		return this.medida * alimento.getCalorias() / alimento.getMedidaDeReferencia();
	}
	
	@Override
	public String toString() {
		return this.alimento.getNombre() + " " + this.medida + " " + this.unidadDeMedida.getNombre();
	}
}