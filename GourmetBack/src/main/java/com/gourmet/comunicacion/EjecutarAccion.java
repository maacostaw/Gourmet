package com.gourmet.comunicacion;

import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;

public enum EjecutarAccion {
	ACTIVADO{
		@Override
		public StatusCode ejecutarAccionRanking(Receta receta,
				Recetario recetario,
				SuscriptorRanking suscriptorRanking,
				AccionRanking accionRanking) {
			return accionRanking.ejecutarAccion(receta, recetario, suscriptorRanking);
		}

		@Override
		public StatusCode ejecutarAccionUsuario(Receta receta,
				Recetario recetario,
				SuscriptorUsuario suscriptorUsuario,
				AccionUsuario accionUsuario) {
			return accionUsuario.ejecutarAccion(receta, recetario, suscriptorUsuario);
		}
	},
	DESACTIVADO{
		@Override
		public StatusCode ejecutarAccionRanking(Receta receta,
				Recetario recetario,
				SuscriptorRanking suscriptorRanking,
				AccionRanking accionRanking) {
			return StatusCode.CODE405;
		}

		@Override
		public StatusCode ejecutarAccionUsuario(Receta receta,
				Recetario recetario,
				SuscriptorUsuario suscriptorUsuario,
				AccionUsuario accionUsuario) {
			return StatusCode.CODE405;
		}
	};
	
    EjecutarAccion() {
    }

	public abstract StatusCode ejecutarAccionRanking(Receta receta,
			Recetario recetario,
			SuscriptorRanking suscriptorRanking,
			AccionRanking accionRanking);
	public abstract StatusCode ejecutarAccionUsuario(Receta receta,
			Recetario recetario,
			SuscriptorUsuario suscriptorUsuario,
			AccionUsuario accionUsuario);
}
