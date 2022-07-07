package com.gourmet.ranking;

import java.util.HashMap;
import java.util.Map;

import com.gourmet.comunicacion.Suscriptor;
import com.gourmet.comunicacion.SuscriptorRanking;
import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;

public class Ranking {
	private String nombre;
	private Map<String, Integer> puntuacionRecetas;
	private Map<String, Suscriptor> suscripciones;

	public Ranking(String nombre) {
		this.nombre = nombre;
		this.puntuacionRecetas = new HashMap<>();
		this.suscripciones = new HashMap<>();
	}
	
	public void agregarSuscripcion(Recetario recetario) {
		Suscriptor nuevoSuscriptor = new SuscriptorRanking(this);
		recetario.getPublicador().suscribirse(nuevoSuscriptor);
		suscripciones.put(recetario.getNombre(), nuevoSuscriptor);
	}
	
	public void desactivarSuscripcion(String nombre) {
		Suscriptor buscado = this.suscripciones.get(nombre);
		if(buscado != null) {
			buscado.desactivar();
		}
	}
	
	public void activarSuscripcion(String nombre) {
		Suscriptor buscado = this.suscripciones.get(nombre);
		if(buscado != null) {
			buscado.activar();
		}
	}
	
	public void agregarReceta(Receta receta) {
		puntuacionRecetas.put(receta.getNombre(), 0);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntuacionReceta(String receta) {
		return puntuacionRecetas.get(receta);
	}
	
	public int setPuntuacionReceta(String receta, int puntuacion) {
		this.puntuacionRecetas.put(receta, puntuacion);
		return this.getPuntuacionReceta(receta);
	}
	
	public Suscriptor getSuscripcion(String nombre) {
		return suscripciones.get(nombre);
	}
}
