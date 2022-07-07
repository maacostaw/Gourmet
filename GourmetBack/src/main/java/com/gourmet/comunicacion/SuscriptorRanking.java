package com.gourmet.comunicacion;

import java.util.ArrayList;
import java.util.List;

import com.gourmet.ranking.Ranking;
import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;

public class SuscriptorRanking implements Suscriptor{
	
	private Ranking ranking;
	private boolean activado;
	private GestionadorDeAcciones gestionadorDeAcciones;
	public List<StatusCode> statusCodes;
	
	public SuscriptorRanking(Ranking ranking) {
		this.ranking = ranking;
		this.activado = true;
		this.statusCodes = new ArrayList<StatusCode>();
		this.gestionadorDeAcciones = GestionadorDeAcciones.getInstancia();
	}

	@Override
	public void notificar(Receta receta, Recetario recetario) {
		for(AccionRanking accionRanking : AccionRanking.values()) {
			EjecutarAccion ejecutarAccion = this.gestionadorDeAcciones.getEjecutarAccionRanking(accionRanking);
			statusCodes.add(ejecutarAccion.ejecutarAccionRanking(receta, recetario, this, accionRanking));
		}
	}

	@Override
	public boolean activado() {
		return this.activado;
	}

	@Override
	public void activar() {
		this.activado = true;
	}
	
	@Override
	public void desactivar() {
		this.activado = false;
	}

	public Ranking getRanking() {
		return ranking;
	}
	
	public List<StatusCode> getStatusCodes() {
		return this.statusCodes;
	}	
}
