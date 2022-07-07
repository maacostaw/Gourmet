package com.gourmet.comunicacion;

import java.util.ArrayList;
import java.util.List;

import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;
import com.gourmet.recetario.enums.Perfil;

public class SuscriptorUsuario implements Suscriptor{
	
	private String email;
	private Perfil perfil;
	private boolean activado;
	private GestionadorDeAcciones gestionadorDeAcciones;
	private Notificacion notificacion;
	public List<StatusCode> statusCodes;
	
	public SuscriptorUsuario(String email, Perfil perfil, boolean activado, Notificacion notificacion) {
		this.email = email;
		this.perfil = perfil;
		this.activado = activado;
		this.notificacion = notificacion;
		this.gestionadorDeAcciones = GestionadorDeAcciones.getInstancia();
		this.statusCodes = new ArrayList<StatusCode>();
	}

	@Override
	public void notificar(Receta receta, Recetario recetario) {
		for(AccionUsuario accionUsuario : AccionUsuario.values()) {
			EjecutarAccion ejecutarAccion = this.gestionadorDeAcciones.getEjecutarAccionUsuario(accionUsuario);
			statusCodes.add(ejecutarAccion.ejecutarAccionUsuario(receta, recetario, this, accionUsuario));
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

	public String getEmail() {
		return email;
	}

	public Perfil getPerfil() {
		return perfil;
	}
	
	public Notificacion getNotificacion(){
		return notificacion;
	}
	public List<StatusCode> getStatusCodes() {
		return this.statusCodes;
	}
}
