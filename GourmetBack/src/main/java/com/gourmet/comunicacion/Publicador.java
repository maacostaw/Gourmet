package com.gourmet.comunicacion;

import java.util.ArrayList;
import java.util.List;

import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;

public class Publicador {
	
	private List<Suscriptor> suscriptores;
		
	public Publicador() {
		suscriptores = new ArrayList<>();
	}
	
	public void suscribirse(Suscriptor suscriptor) {
		this.suscriptores.add(suscriptor);
	}
	
	public void retirarSuscripcion(Suscriptor suscriptor) {
		this.suscriptores.remove(suscriptor);
	}
	
	public void notificar(Receta receta, Recetario recetario) {
		for(Suscriptor suscriptor : this.suscriptores) {
			if(suscriptor.activado()) {
				suscriptor.notificar(receta,recetario);
			}
		}
	}
}
