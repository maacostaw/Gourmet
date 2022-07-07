package com.gourmet.comunicacion;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gourmet.ranking.Ranking;
import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;

/**
 * Unit test para la clase recetario.
 */
public class AccionRankingTest {
		
	//Guardará 5 recetarios
	private List<Recetario> recetarios;
	//Guarda la receta
	private Receta mockedReceta;
	//Guarda el ranking 1, que esta suscrito a 3 recetarios
	private Ranking ranking1;
	//Guarda el ranking 2, que esta suscrito a 5 recetarios
	private Ranking ranking2;

	
	@Before
	public void setUp() throws Exception{
		recetarios = new ArrayList<Recetario>();
		
		for(int i = 1; i<=5 ; i++) {
			Recetario nuevoRecetario = new Recetario("Recetario " + i,"");
			recetarios.add(nuevoRecetario);
		}
		
		this.mockedReceta = mock(Receta.class);
		when(mockedReceta.getNombre()).thenReturn("Chuleta Valluna");
		
		this.ranking1 = new Ranking("Ranking de carnes");
		this.ranking1.agregarReceta(mockedReceta);
		
		for(int i = 0; i<3 ; i++) {
			ranking1.agregarSuscripcion(recetarios.get(i));
		}
		
		this.ranking2 = new Ranking("Ranking de carnes rojas");
		this.ranking2.agregarReceta(mockedReceta);
		
		for(int i = 0; i<5 ; i++) {
			ranking2.agregarSuscripcion(recetarios.get(i));
		}
	}
	
	/**
     * Accion a Probar: AGREGAR_PUNTOS
     * Escenario: Agregar una receta a 5 recetarios distintos,
     * el ranking 1 esta suscrito a 3 de esos
     * Resultado: Esa receta tendrá 30 puntos
     */
	@Test 
	public void when_AgregoRecetas__with_AGREGAR_PUNTOSActivada_then_RecetaEnRanking1Tiene30Puntos(){
		for(int i = 0; i<5 ; i++) {
			recetarios.get(i).addReceta(mockedReceta);
		}
		assertEquals(30, ranking1.getPuntuacionReceta(mockedReceta.getNombre()));
	}
	
	/**
     * Accion a Probar: AGREGAR_PUNTOS
     * Escenario: Agregar una receta a 5 recetarios distintos,
     * el ranking 2 esta suscrito a 5 de esos
     * Resultado: Esa receta tendrá 50 puntos
     */
	@Test 
	public void when_AgregoRecetas__with_AGREGAR_PUNTOSActivada_then_RecetaEnRanking2Tiene50Puntos(){
		for(int i = 0; i<5 ; i++) {
			recetarios.get(i).addReceta(mockedReceta);
		}
		assertEquals(50, ranking2.getPuntuacionReceta(mockedReceta.getNombre()));
	}
	
	/**
     * Accion a Probar: AGREGAR_PUNTOS
     * Escenario: Agregar una receta a 5 recetarios distintos,
     * la accion AGREGAR_PUNTOS esta deshabilitada
     * Resultado: Esa receta tendrá 0 puntos
     */
	@Test 
	public void when_AgregoRecetas__with_AGREGAR_PUNTOSDesactivada_then_RecetaEnRanking2Tiene0Puntos(){
		GestionadorDeAcciones.getInstancia().desactivarAccionRanking(AccionRanking.AGREGAR_PUNTOS);
		for(int i = 0; i<5 ; i++) {
			recetarios.get(i).addReceta(mockedReceta);
		}
		assertEquals(0, ranking2.getPuntuacionReceta(mockedReceta.getNombre()));
	}
	
	/**
     * Accion a Probar: AGREGAR_PUNTOS
     * Escenario: Agrego una receta a un recetario con AGREGAR_PUNTOS
     * activo (+10) luego desactivo la AGREGAR_PUNTOS, agrego a 3 recetarios
     * (+0), luego vuelvo a activar ACTIVAR_PUNTOS y agrego la receta al 
     * ultimo recetario (+10)
     * Resultado: Esa receta tendrá 20 puntos
     */
	@Test 
	public void when_AgregoRecetas_with_AGREGAR_PUNTOSActivadaAVeces_and_AGREGAR_PUNTOSDesactivadaAVeces_then_RecetaEnRanking2Tiene20Puntos(){
		recetarios.get(0).addReceta(mockedReceta);
		assertEquals(10, ranking2.getPuntuacionReceta(mockedReceta.getNombre()));
		GestionadorDeAcciones.getInstancia().desactivarAccionRanking(AccionRanking.AGREGAR_PUNTOS);
		recetarios.get(1).addReceta(mockedReceta);
		recetarios.get(2).addReceta(mockedReceta);
		recetarios.get(3).addReceta(mockedReceta);
		assertEquals(10, ranking2.getPuntuacionReceta(mockedReceta.getNombre()));
		GestionadorDeAcciones.getInstancia().activarAccionRanking(AccionRanking.AGREGAR_PUNTOS);
		recetarios.get(4).addReceta(mockedReceta);
		assertEquals(20, ranking2.getPuntuacionReceta(mockedReceta.getNombre()));
	}
	
	@After
	public void resetSetUp() {
		GestionadorDeAcciones.getInstancia().activarAccionRanking(AccionRanking.AGREGAR_PUNTOS);
	}
}