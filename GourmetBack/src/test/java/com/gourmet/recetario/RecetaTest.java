package com.gourmet.recetario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.gourmet.recetario.entities.Alimento;
import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.enums.GrupoAlimenticio;
import com.gourmet.recetario.enums.UnidadDeMedida;
import com.gourmet.recetario.exceptions.UnidadDeMedidaException;

/**
 * Unit test para la clase receta.
 */
public class RecetaTest {
	
	private Receta receta1;
	
	//Ingredientes usados en receta 1
	private Alimento pasta;
	private Alimento arroz;
	private Alimento chile;
	//Ingredientes disponibles
	private Alimento tomate;
	private Alimento salsa;
	
	/**
	 * Crea una receta con pasta (gramos), arroz (gramos) y chile ( unidades)
	 */
	@Before
	public void setUp() throws Exception{
    	receta1 = new Receta("Pasta con arroz y chile","");
    	
    	pasta = new Alimento("Pasta",131,100,UnidadDeMedida.GRAMOS,GrupoAlimenticio.CEREALES);
    	arroz = new Alimento("Arroz",130,100,UnidadDeMedida.GRAMOS,GrupoAlimenticio.CEREALES);
    	chile = new Alimento("Chile",100,1,UnidadDeMedida.UNIDAD,GrupoAlimenticio.VEGETALES);
    	
    	tomate = new Alimento("Tomate",200,1,UnidadDeMedida.UNIDAD,GrupoAlimenticio.VEGETALES);
    	salsa = new Alimento("Salsa de tomate",500,100,UnidadDeMedida.GRAMOS,GrupoAlimenticio.VEGETALES);

    	
    	receta1.addIngredienteNecesario(200,UnidadDeMedida.GRAMOS, pasta);
    	receta1.addIngredienteNecesario(100,UnidadDeMedida.GRAMOS, arroz);
    	receta1.addIngredienteNecesario(1,UnidadDeMedida.UNIDAD, chile);
	}
	
	/**
     * Metodo a Probar: numeroDeIngredientes
     * Escenario: se llama el metodo con una receta de tamaño 3
     * Resultado: 3
     */
    @Test
    public void when_NumeroDeIngredientes_isCalled_with_RecetaDeTamanio3_return_3()
    {
        assertEquals(3, receta1.numeroDeIngredientes());
    }
    
    /**
     * Metodo a probar: numeroDeIngredientes
     * Escenario: se llama el metodo con una receta nueva (tamaño 0)
     * Resultado: 0
     */
    @Test
    public void when_NumeroDeIngredientes_isCalled_with_RecetaNuevo_return_0()
    {
    	Receta receta2 = new Receta("Receta nueva","");
        assertEquals(0, receta2.numeroDeIngredientes());
    }
    
    /**
     * Metodo a Probar: numeroDeIngredientes
     * Escenario: se llama el metodo luego de adicionar un ingrediente
     * a una receta que tiene 3 ingredientes
     * Resultado: 4
     */
    @Test
    public void when_NumeroDeIngredientes_isCalled_with_RecetaDeTamanio3MasNuevoIngrediente_return_4() throws Exception
    {
    	assertEquals(3, receta1.numeroDeIngredientes());
    	receta1.addIngredienteNecesario(2,UnidadDeMedida.UNIDAD,tomate);
        assertEquals(4, receta1.numeroDeIngredientes());
    }
    
    /**
     * Metodo a Probar: numeroDeIngredientes
     * Escenario: se llama el metodo luego de intentar adicionar 
     * un ingrediente usando una unidad de medida distinta a la
     * establecida por el alimento, la receta que tiene 3 ingredientes
     * originalmente
     * Resultado: 3
     */
    @Test(expected = UnidadDeMedidaException.class)
    public void when_NumeroDeIngredientes_isCalled_with_RecetaDeTamanio3MasNuevoIngredienteInvalido_throw_exception() throws Exception{
    	receta1.addIngredienteNecesario(400,UnidadDeMedida.GRAMOS,tomate);
    }
    
    /**
     * Metodo a Probar: calcularCalorias
     * Escenario: Receta setup (solo ingredientes necesarios)
     * 200 gr de pasta (131 calorias x 100 gr) = 262
     * 100 gr de arroz (130 calorias x 100 gr) = 130
     * 1 chile (100 calorias x 1 unidad)       = 100
     * total                                   = 492
     * Resultado: 492
     */
    @Test
    public void when_calcularCalorias_isCalled_with_RecetaDe492Calorias_return_492() {
    	assertEquals(492, receta1.calcularCalorias());
    }
    
    /**
     * Metodo a Probar: calcularCalorias
     * Escenario: Receta setup más un ingrediente opcional
     * receta de pasta con arroz sin salsa     = 492
     * salsa                                   = 0
     * total                                   = 492
     * Resultado: 492
     */
    @Test
    public void when_calcularCalorias_isCalled_with_RecetaDe492CaloriasMasIngredienteOpcional_return_492() throws Exception{
    	receta1.addIngredienteOpcional(salsa);
    	assertEquals(492, receta1.calcularCalorias());
    }
    
    /**
     * Metodo a Probar: getIngrediente
     * Escenario: Busco pasta en una receta que tiene pasta
     * Resultado: (Alimento) pasta
     */
    @Test
    public void when_getIngrediente_isCalled_with_BuscandoPastaEnUnaRecetaConPasta_return_Pasta() {
    	assertTrue(receta1.getIngrediente("Pasta") != null);
    }
    
    /**
     * Metodo a Probar: getIngrediente
     * Escenario: Busco frijoles en una receta que no tiene frijoles
     * Resultado: null
     */
    @Test
    public void when_getIngrediente_isCalled_with_BuscandoFrijolesEnUnaRecetaSinFrijoles_return_null() {
    	assertTrue(receta1.getIngrediente("Frijoles") == null);
    }
    
    /**
     * Metodo a Probar: hayGrupoAlimenticio
     * Escenario: Busco el grupo alimenticio Cereales en una receta 
     * que tiene Cereales
     * Resultado: true
     */
    @Test
    public void when_hayGrupoAlimenticio_isCalled_with_BuscandoCerealesEnUnaRecetaConCereales_return_true() {
    	assertTrue(receta1.hayGrupoAlimenticio(GrupoAlimenticio.CEREALES));
    }
    
    /**
     * Metodo a Probar: hayGrupoAlimenticio
     * Escenario: Busco el grupo alimenticio Frutas en una receta 
     * que no tiene Frutas
     * Resultado: false
     */
    @Test
    public void when_hayGrupoAlimenticio_isCalled_with_BuscandoFrutasEnUnaRecetaConFrutas_return_false() {
    	assertFalse(receta1.hayGrupoAlimenticio(GrupoAlimenticio.FRUTAS));
    }
}
