package com.gourmet.usuario;

import java.util.HashMap;
import java.util.Map;

import com.gourmet.comunicacion.Notificacion;
import com.gourmet.comunicacion.Suscriptor;
import com.gourmet.comunicacion.SuscriptorUsuario;
import com.gourmet.recetario.entities.Recetario;
import com.gourmet.recetario.enums.Perfil;

public class Usuario {
	private Map<String, Suscriptor> suscripciones;

	public Usuario() {
		suscripciones = new HashMap<>();
	}
	
	public void agregarSuscripcion(Recetario recetario, String correo, Perfil perfil, Notificacion notificacion) {
		Suscriptor nuevoSuscriptor = new SuscriptorUsuario(correo, perfil, true, notificacion);
		recetario.getPublicador().suscribirse(nuevoSuscriptor);
		String suscripcionNombre = recetario.getNombre() + " " + perfil.getNombre();
		this.suscripciones.put(suscripcionNombre, nuevoSuscriptor);
	}
	
	public Suscriptor getSuscripcion(String suscripcionNombre) {
		return suscripciones.get(suscripcionNombre);
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
}
