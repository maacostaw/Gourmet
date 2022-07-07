package com.gourmet.recetario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.gourmet.recetario.entities.Alimento;
import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.enums.GrupoAlimenticio;
import com.gourmet.recetario.enums.Perfil;
import com.gourmet.recetario.enums.UnidadDeMedida;

/**
 * Unit test para la clase recetario.
 */
public class PerfilesTest {
	
	private Receta fresasConCrema;	
	private Receta raviolisConCarne;
	private Receta pinchoDeCarne;	
	private Receta tofuAsiatico;	

	//Ingredientes base
	private Alimento fresa;
	private Alimento cremaDeLeche;
	private Alimento pasta;
	private Alimento carne;
	private Alimento tofu;
	
	//Ingredientes de reserva
	private Alimento chocolate;
	private Alimento queso;
		
	/*
	 * Crea las fresas con crema, los raviolis de carne, frijolada
	 * el pincho de carne y tofu
	 */
	@Before
	public void setUp() throws Exception{
		fresasConCrema = new Receta("Fresas con crema","");
		
		fresa = new Alimento("Fresa",15,4,UnidadDeMedida.UNIDAD,GrupoAlimenticio.FRUTAS);
		cremaDeLeche = new Alimento("Crema de Leche",196,100,UnidadDeMedida.GRAMOS,GrupoAlimenticio.LACTEOS);
		
		fresasConCrema.addIngredienteNecesario(15, UnidadDeMedida.UNIDAD, fresa);
		fresasConCrema.addIngredienteNecesario(400, UnidadDeMedida.GRAMOS, cremaDeLeche);
		
		raviolisConCarne = new Receta("Raviolis con Carne","");
		
		pasta = new Alimento("Pasta",131,100,UnidadDeMedida.GRAMOS,GrupoAlimenticio.CEREALES);
		carne = new Alimento("Carne",143,100,UnidadDeMedida.GRAMOS,GrupoAlimenticio.CARNES);
		
		raviolisConCarne.addIngredienteNecesario(300, UnidadDeMedida.GRAMOS, pasta);
		raviolisConCarne.addIngredienteNecesario(200, UnidadDeMedida.GRAMOS, carne);
		
		pinchoDeCarne = new Receta("Pincho de Carne","");
		
		pinchoDeCarne.addIngredienteNecesario(125, UnidadDeMedida.GRAMOS, carne);
		
		tofuAsiatico = new Receta("Tofu","");
		
		tofu = new Alimento("Tofu",76,100,UnidadDeMedida.GRAMOS,GrupoAlimenticio.VEGETALES);
		tofuAsiatico.addIngredienteNecesario(200, UnidadDeMedida.GRAMOS, tofu);	
				
		chocolate = new Alimento("Chocolate",546,100,UnidadDeMedida.GRAMOS,GrupoAlimenticio.LACTEOS);
		queso = new Alimento("Queso",402,100,UnidadDeMedida.GRAMOS,GrupoAlimenticio.LACTEOS);

	}
	
	/**
     * Metodo a Probar: cumpleElPerfil
     * Escenario: Se evalua el perfil Celaico para la receta de fresas con crema
     * Resultado: true
     */
	@Test 
	public void when_CumpleElPerfil_isCalled_with_RecetaSinCereales_return_true(){
		assertTrue(fresasConCrema.cumpleElPerfil(Perfil.CELIACO));
	}
	
	/**
     * Metodo a Probar: cumpleElPerfil
     * Escenario: Se evalua el perfil Celaico para la receta raviolis de carne
     * Resultado: false
     */
	@Test 
	public void when_CumpleElPerfil_isCalled_with_RecetaConCereales_return_false(){
		assertFalse(raviolisConCarne.cumpleElPerfil(Perfil.CELIACO));
	}
	
	/**
     * Metodo a Probar: cumpleElPerfil
     * Escenario: Se evalua el perfil Vegetariano para la receta de fresas con crema
     * Resultado: true
     */
	@Test 
	public void when_CumpleElPerfil_isCalled_with_RecetaSinCarnes_return_true(){
		assertTrue(fresasConCrema.cumpleElPerfil(Perfil.VEGETARIANO));
	}
	
	/**
     * Metodo a Probar: cumpleElPerfil
     * Escenario: Se evalua el perfil Vegetariano para la receta de raviolis con carne
     * Resultado: false
     */
	@Test 
	public void when_CumpleElPerfil_isCalled_with_RecetaConCarnes_return_false(){
		assertFalse(raviolisConCarne.cumpleElPerfil(Perfil.VEGETARIANO));
	}
	
	/**
     * Metodo a Probar: cumpleElPerfil
     * Escenario: Se evalua el perfil VEGANO para la receta frijolada
     * Resultado: true
     */
	@Test 
	public void when_CumpleElPerfil_isCalled_with_RecetaSinCarnesSinLacteos_return_true(){
		assertTrue(tofuAsiatico.cumpleElPerfil(Perfil.VEGANO));

	}
	
	/**
     * Metodo a Probar: cumpleElPerfil
     * Escenario: Se evalua el perfil VEGANO para la receta de tofu
     * al añadirle chocolate, no tiene carnes pero ahora tendría lacteos
     * Resultado: false
     */
	@Test 
	public void when_CumpleElPerfil_isCalled_with_RecetaSinCarnesConLacteos_return_false(){
		tofuAsiatico.addIngredienteOpcional(chocolate);
		assertFalse(tofuAsiatico.cumpleElPerfil(Perfil.VEGANO));
	}
	
	/**
     * Metodo a Probar: cumpleElPerfil
     * Escenario: Se evalua el perfil VEGANO para la receta de raviolis
     * con carne, no tiene lacteos pero tiene carne
     * Resultado: false
     */
	@Test 
	public void when_CumpleElPerfil_isCalled_with_RecetaConCarnesSinLacteos_return_false(){
		assertFalse(raviolisConCarne.cumpleElPerfil(Perfil.VEGANO));
	}
	
	/**
     * Metodo a Probar: cumpleElPerfil
     * Escenario: Se evalua el perfil VEGANO para la receta de raviolis
     * con carne, al añadirle lacteos, tendria tanto lacteos como carne.
     * Resultado: false
     */
	@Test 
	public void when_CumpleElPerfil_isCalled_with_RecetaConCarnesConLacteos_return_false(){
		raviolisConCarne.addIngredienteOpcional(queso);
		assertFalse(raviolisConCarne.cumpleElPerfil(Perfil.VEGANO));
	}
	
	/**
     * Metodo a Probar: cumpleElPerfil
     * Escenario: Se evalua el perfil CARNIVORO para la receta raviolis con carne,
     * que tiene tanto carne como más de 200 calorias
     * Resultado: true
     */
	@Test
	public void when_CumpleElPerfil_isCalled_with_RecetaConCarnesConMas200Cal_return_true() {
		assertTrue(raviolisConCarne.cumpleElPerfil(Perfil.CARNIVORO));
	}
	
	/**
     * Metodo a Probar: cumpleElPerfil
     * Escenario: Se evalua el perfil CARNIVORO para la receta fresas con crema,
     * que tiene más de 200 calorias pero sin carnes
     * Resultado: false
     */
	@Test
	public void when_CumpleElPerfil_isCalled_with_RecetaSinCarnesConMas200Cal_return_false() {
		assertFalse(fresasConCrema.cumpleElPerfil(Perfil.CARNIVORO));
	}
	
	/**
     * Metodo a Probar: cumpleElPerfil
     * Escenario: Se evalua el perfil CARNIVORO para la receta pincho de carne,
     * que tiene carnes pero menos de 200 calorias
     * Resultado: false
     */
	@Test
	public void when_CumpleElPerfil_isCalled_with_RecetaConCarnesSinMas200Cal_return_false() {
		assertFalse(pinchoDeCarne.cumpleElPerfil(Perfil.CARNIVORO));
	}
	
	/**
     * Metodo a Probar: cumpleElPerfil
     * Escenario: Se evalua el perfil CARNIVORO para la receta tofu,
     * que no tiene carnes y no tiene más de 200 calorias
     * Resultado: false
     */
	@Test
	public void when_CumpleElPerfil_isCalled_with_RecetaSinCarnesSinMas200Cal_return_false() {
		assertFalse(tofuAsiatico.cumpleElPerfil(Perfil.CARNIVORO));
	}
}