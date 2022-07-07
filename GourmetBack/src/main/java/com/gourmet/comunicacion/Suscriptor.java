package com.gourmet.comunicacion;

import java.util.List;

import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;

public interface Suscriptor {
	public void notificar(Receta receta, Recetario recetario);
	public boolean activado();
	public void activar();
	public void desactivar();
	public List<StatusCode> getStatusCodes();
}
