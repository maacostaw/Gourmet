package com.gourmet.recetario.enums;

import com.gourmet.recetario.entities.IngredienteAbstract;
import com.gourmet.recetario.entities.Receta;

public enum Perfil {
	CELIACO("Celíaco"){
		@Override
		public boolean evaluarPerfil(Receta receta) {
			boolean contieneCereales = false;
			for(IngredienteAbstract ingrediente : receta.getIngredientes()) {
				GrupoAlimenticio grupoAlimenticioIngrediente = ingrediente.getAlimento().getGrupoAlimenticio();
				if(grupoAlimenticioIngrediente == GrupoAlimenticio.CEREALES) {
					contieneCereales = true;
					break;
				}
			}
			return !contieneCereales;
		}
	},VEGETARIANO("Vegetariano"){
		@Override
		public boolean evaluarPerfil(Receta receta) {
			boolean contieneCarnes = false;
			for(IngredienteAbstract ingrediente : receta.getIngredientes()) {
				GrupoAlimenticio grupoAlimenticioIngrediente = ingrediente.getAlimento().getGrupoAlimenticio();
				if(grupoAlimenticioIngrediente == GrupoAlimenticio.CARNES) {
					contieneCarnes = true;
					break;
				}
			}
			return !contieneCarnes;
		}
	},VEGANO("Vegano"){
		@Override
		public boolean evaluarPerfil(Receta receta) {
			boolean contieneCarnes = false;
			boolean contieneLacteos = false;
			for(IngredienteAbstract ingrediente : receta.getIngredientes()) {
				GrupoAlimenticio grupoAlimenticioIngrediente = ingrediente.getAlimento().getGrupoAlimenticio();
				if(grupoAlimenticioIngrediente == GrupoAlimenticio.CARNES) {
					contieneCarnes = true;
					break;
				}
				if(grupoAlimenticioIngrediente == GrupoAlimenticio.LACTEOS) {
					contieneLacteos = true;
					break;
				}
			}
			return !contieneCarnes && !contieneLacteos;
		}
	},CARNIVORO("Carnivoro"){
		@Override
		public boolean evaluarPerfil(Receta receta) {
			boolean contieneCarnes = false;
			for(IngredienteAbstract ingrediente : receta.getIngredientes()) {
				GrupoAlimenticio grupoAlimenticioIngrediente = ingrediente.getAlimento().getGrupoAlimenticio();
				if(grupoAlimenticioIngrediente == GrupoAlimenticio.CARNES) {
					contieneCarnes = true;
					break;
				}
			}
			boolean masDe200Calorias = false;
			float caloriasIngrediente = receta.calcularCalorias();
			if(caloriasIngrediente>200) {
				masDe200Calorias = true;
			}
			return contieneCarnes && masDe200Calorias;
		}
	};
	
	private final String nombre;
	
	Perfil(String nombre){
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	public abstract boolean evaluarPerfil(Receta receta);
}
