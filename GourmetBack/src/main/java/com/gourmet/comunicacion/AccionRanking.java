package com.gourmet.comunicacion;

import com.gourmet.ranking.Ranking;
import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;

public enum AccionRanking {
	AGREGAR_PUNTOS {
		@Override
		public StatusCode ejecutarAccion(Receta receta, 
				Recetario recetario, 
				SuscriptorRanking suscriptorRanking){
			Ranking ranking = suscriptorRanking.getRanking();
			int puntuacionReceta = ranking.getPuntuacionReceta(receta.getNombre());
			ranking.setPuntuacionReceta(receta.getNombre(), puntuacionReceta+10);
			return StatusCode.CODE200;
		}
	};
	
	AccionRanking(){}
	
	public abstract StatusCode ejecutarAccion(Receta receta, 
			Recetario recetario,
			SuscriptorRanking suscriptorRanking);

}
