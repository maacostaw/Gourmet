package com.gourmet.comunicacion;

import java.util.HashMap;
import java.util.Map;

public final class GestionadorDeAcciones {
	
	private static GestionadorDeAcciones instancia;
	
	private Map<AccionRanking, EjecutarAccion> ejecutarAccionRanking;
	private Map<AccionUsuario, EjecutarAccion> ejecutarAccionUsuario;
	
	private GestionadorDeAcciones() {
		ejecutarAccionRanking = new HashMap<AccionRanking, EjecutarAccion>();
		ejecutarAccionUsuario = new HashMap<AccionUsuario, EjecutarAccion>();
		for(AccionRanking accionRanking : AccionRanking.values()) {
			ejecutarAccionRanking.put(accionRanking,EjecutarAccion.ACTIVADO);
		}
		for(AccionUsuario accionUsuario : AccionUsuario.values()) {
			ejecutarAccionUsuario.put(accionUsuario,EjecutarAccion.ACTIVADO);
		}
	}

	public EjecutarAccion getEjecutarAccionUsuario(AccionUsuario accionUsuario) {
		return ejecutarAccionUsuario.get(accionUsuario);
	}
	
	public EjecutarAccion getEjecutarAccionRanking(AccionRanking accionRanking) {
		return ejecutarAccionRanking.get(accionRanking);
	}
	
	public void activarAccionUsuario(AccionUsuario accionUsuario) {
		this.ejecutarAccionUsuario.put(accionUsuario, EjecutarAccion.ACTIVADO);
	}
	
	public void desactivarAccionUsuario(AccionUsuario accionUsuario) {
		this.ejecutarAccionUsuario.put(accionUsuario, EjecutarAccion.DESACTIVADO);
	}

	public void activarAccionRanking(AccionRanking accionRanking) {
		this.ejecutarAccionRanking.put(accionRanking, EjecutarAccion.ACTIVADO);
	}
	
	public void desactivarAccionRanking(AccionRanking accionRanking) {
		this.ejecutarAccionRanking.put(accionRanking, EjecutarAccion.DESACTIVADO);
	}
	
	public static GestionadorDeAcciones getInstancia() {
		if(instancia == null) {
			instancia = new GestionadorDeAcciones();
		}
		return instancia;
	}
}
