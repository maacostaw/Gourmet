package com.gourmet.comunicacion;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;
import com.gourmet.recetario.enums.Perfil;
import com.gourmet.usuario.Usuario;

/**
 * Unit test para la clase recetario.
 */
public class AccionUsuarioTest {
		
	private Recetario recetario;
	
	private Receta recetaCarnivoro;
	private Receta recetaVegano;
	
	private Usuario miguel;
	
	private Notificacion notificacion;

	/**
	 * Creo:
	 * 1 recetario
	 * 2 recetas, una carnivora y otra vegano
	 * 1 usuario
	 * 1 mock de la interfaz notificación
	 * 1 suscripción al recetario con perfil vegano
	 * Le doy comportamiento al mock para que solo cuando se mande
	 * una receta de tipo vegano al recetario 1 este devuelva un resultado
	 */
	@Before
	public void setUp(){
		recetario = new Recetario("Recetario 1","");
		
		recetaCarnivoro = mock(Receta.class);
		when(recetaCarnivoro.getNombre()).thenReturn("Receta carnivora");
		when(recetaCarnivoro.cumpleElPerfil(Perfil.CARNIVORO)).thenReturn(true);
		
		recetaVegano = mock(Receta.class);
		when(recetaVegano.getNombre()).thenReturn("Receta vegano");
		when(recetaVegano.cumpleElPerfil(Perfil.VEGANO)).thenReturn(true);
		
		
		notificacion = mock(Notificacion.class);
				
		miguel = new Usuario();
		miguel.agregarSuscripcion(recetario, "maacosta@hexacta.com", Perfil.VEGANO, notificacion);
		
		when(notificacion.publicar("maacosta@hexacta.com Receta vegano Recetario 1")).thenReturn(StatusCode.CODE200);
	}
	
	/**
     * Accion a Probar: notificar (usuario)
     * Escenario: agrego 1 receta de perfil vegano al recetario
     * Resultado: Recibo un CODE200 para la suscripción vegana ya que
     * solo si el programa funciona el mock devolverá ese código
     */
	@Test 
	public void when_Notificar_isCalled_with_RecetaVegano_return_CODE200(){
		
		recetario.addReceta(recetaVegano);
		
		int numeroDeCODE200 = 0;
		
		for(StatusCode statusCode : miguel.getSuscripcion("Recetario 1 Vegano").getStatusCodes()) {
			if(statusCode == StatusCode.CODE200) {
				numeroDeCODE200++;
			} 

		}
		
		assertEquals(numeroDeCODE200,1);
	}
	
	/**
     * Accion a Probar: notificar (usuario)
     * Escenario: agrego 1 receta de perfil vegano al recetario con
     * la acción deshabilitada
     * Resultado: Recibo un CODE405 ya que la acción de avisar al
     * usuario esta deshabilitada
     */
	@Test 
	public void when_Notificar_isCalled_with_RecetaVegano_y_notificarDesactivado_return_CODE405(){
		
		GestionadorDeAcciones.getInstancia().desactivarAccionUsuario(AccionUsuario.AVISAR_USUARIO);
		
		recetario.addReceta(recetaVegano);
		
		int numeroDeCODE405 = 0;
		
		for(StatusCode statusCode : miguel.getSuscripcion("Recetario 1 Vegano").getStatusCodes()) {
			if(statusCode == StatusCode.CODE405) {
				numeroDeCODE405++;
			} 

		}
		
		assertEquals(numeroDeCODE405,1);
	}
	
	/**
     * Accion a Probar: notificar (usuario)
     * Escenario: agrego 1 receta de perfil carnivoro al recetario
     * Resultado: Recibo un CODE202 para la suscripción vegana ya que 
     * el perfil no se cumple y no se manda el pedido a la interfaz
     * notificación
     */
	@Test 
	public void when_Notificar_isCalled_with_RecetaCarnivoro_return_CODE202(){
		
		recetario.addReceta(recetaCarnivoro);
		
		int numeroDeCODE202 = 0;
		
		for(StatusCode statusCode : miguel.getSuscripcion("Recetario 1 Vegano").getStatusCodes()) {
			if(statusCode == StatusCode.CODE202) {
				numeroDeCODE202++;
			} 

		}
		
		assertEquals(numeroDeCODE202,1);
	}
	
	/**
     * Accion a Probar: notificar (usuario)
     * Escenario: agrego 1 receta de perfil carnivoro al recetario con
     * la acción deshabilitada
     * Resultado: Recibo un CODE405 ya que la acción de avisar al
     * usuario esta deshabilitada
     */
	@Test 
	public void when_Notificar_isCalled_with_RecetaCarnivoro_y_notificarDesactivado_return_CODE405(){
		GestionadorDeAcciones.getInstancia().desactivarAccionUsuario(AccionUsuario.AVISAR_USUARIO);
		
		recetario.addReceta(recetaCarnivoro);
		
		int numeroDeCODE405 = 0;
		
		for(StatusCode statusCode : miguel.getSuscripcion("Recetario 1 Vegano").getStatusCodes()) {
			if(statusCode == StatusCode.CODE405) {
				numeroDeCODE405++;
			} 

		}
		
		assertEquals(numeroDeCODE405,1);
	}
	
	@After
	public void resetSetUp() {
		GestionadorDeAcciones.getInstancia().activarAccionUsuario(AccionUsuario.AVISAR_USUARIO);
	}
}