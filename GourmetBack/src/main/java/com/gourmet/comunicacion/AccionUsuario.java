package com.gourmet.comunicacion;

import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;
import com.gourmet.recetario.enums.Perfil;

public enum AccionUsuario {
	
	AVISAR_USUARIO {
		@Override
		public StatusCode ejecutarAccion(Receta receta, 
				Recetario recetario, 
				SuscriptorUsuario suscriptorUsuario) {
			Perfil perfil = suscriptorUsuario.getPerfil();
			String email = suscriptorUsuario.getEmail();
			Notificacion notificacion = suscriptorUsuario.getNotificacion();
			if (receta.cumpleElPerfil(perfil)) {
				return notificacion.publicar(email + " " + receta.getNombre() + " " + recetario.getNombre());
			}
			return StatusCode.CODE202;
		}
	};
	
	AccionUsuario(){}

	public abstract StatusCode ejecutarAccion(Receta receta, 
			Recetario recetario, 
			SuscriptorUsuario suscriptorUsuario);

}
