package com.gourmet.recetario;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;

/**
 * Unit test para la clase recetario.
 */
public class RecetarioTest {
	
	private Recetario recetario1;
	
	/*
	 * Crea un recetario con 3 recetas
	 */
	@Before
	public void setUp() {
		recetario1 = new Recetario("Recetario de Pastas","");
    	
    	Receta receta1 = new Receta("Pasta con carne","");
    	Receta receta2 = new Receta("Pasta con arroz y chile","");
    	Receta receta3 = new Receta("Pasta con Queso","");
    	
    	recetario1.addReceta(receta1);
    	recetario1.addReceta(receta2);
    	recetario1.addReceta(receta3);
	}
	
    /**
     * Metodo a Probar: getCantidadRecetas
     * Escenario: se llama el metodo con un recetario de tamaño 3
     * Resultado: 3
     */
    @Test
    public void when_getCantidadRecetas_isCalled_with_RecetarioDeTamanio3_return_3()
    {
        assertEquals(3, recetario1.getCantidadRecetas());
    }
    
    /**
     * Metodo a Probar: getCantidadRecetas
     * Escenario: se llama el metodo con un recetario nuevo (tamaño 0)
     * Resultado: 0
     */
    @Test
    public void when_getCantidadRecetas_isCalled_with_RecetarioNuevo_return_0()
    {
    	Recetario recetario2 = new Recetario("Recetario nuevo","");
        assertEquals(0, recetario2.getCantidadRecetas());
    }
    
    /**
     * Metodo a Probar: getCantidadRecetas
     * Escenario: se llama el metodo luego de adicionar una receta
     * a un recetario que tiene 3 elementos
     * Resultado: 4
     */
    @Test
    public void when_getCantidadRecetas_isCalled_with_RecetarioDeTamanio3MasNuevaReceta_return_4()
    {
    	assertEquals(3, recetario1.getCantidadRecetas());
    	Receta receta4 = new Receta("Pasta con salsa","");
    	recetario1.addReceta(receta4);
        assertEquals(4, recetario1.getCantidadRecetas());
    }
}
