package com.gourmet.recetario.service;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gourmet.App;
import com.gourmet.recetario.entities.Recetario;

@Transactional
@RunWith(SpringRunner.class) 
@SpringBootTest(classes=App.class)
public class RecetarioServiceTest {
	@Autowired
	private RecetarioService recetarioService;
	
	@Test
	public void when_addRecetario_isCalled_CreateNuevoRecetario() throws EntityNotFoundException{
		Recetario malteadas = new Recetario("Recetario de malteadas","");
		Recetario resultado = recetarioService.addRecetario(malteadas);
		Recetario buscado = recetarioService.getRecetario(malteadas.getId());
		
		boolean sameId = resultado.getId().equals(buscado.getId());
		boolean sameNombre = resultado.getNombre().equals(buscado.getNombre());
		
		boolean sameProperties = sameId && sameNombre;
		
        assertTrue(sameProperties);
	}
	
	@Test
	public void when_getRecetario_isCalled_and_RecetarioExiste_TraerRecetarioBuscado() throws EntityNotFoundException{
		Recetario ensaladas = new Recetario("Recetario de ensaladas","");
		recetarioService.addRecetario(ensaladas);
		
		Recetario buscado = recetarioService.getRecetario(ensaladas.getId());
		
		boolean sameId = ensaladas.getId().equals(buscado.getId());
		boolean sameNombre = ensaladas.getNombre().equals(buscado.getNombre());
		
		boolean sameProperties = sameId && sameNombre;
		
        assertTrue(sameProperties);
	}
	
	@Test(expected = Exception.class)
	public void when_getRecetario_isCalled_and_RecetarioNoExiste_ThrowError() throws EntityNotFoundException{
		recetarioService.getRecetario((long) -1);
	}
	
	@Test
	public void when_updateRecetario_isCalled_and_RecetarioExiste_CambiarNombre() throws EntityNotFoundException{
		Recetario carnes1 = new Recetario("Recetario de carnes 1.0","");
		Recetario carnes2 = new Recetario("Recetario de carnes 2.0","");

		Recetario original = recetarioService.addRecetario(carnes1);
						
		Recetario actualizado = recetarioService.updateRecetario(carnes1.getId(), carnes2);
		
		boolean sameId = original.getId().equals(actualizado.getId());
		boolean sameNombre = original.getNombre().equals(actualizado.getNombre());
		
		boolean sameProperties = sameId && sameNombre;
		
        assertTrue(sameProperties);
	}
	
	@Test(expected = Exception.class)
	public void when_deleteRecetario_isCalled_andRecetarioExiste_RecetarioEliminado() {
		Recetario frutas = new Recetario("Recetario de frutas","");
		recetarioService.addRecetario(frutas);
		Long recetarioId = frutas.getId();
		recetarioService.deleteRecetario(recetarioId);
		recetarioService.getRecetario(recetarioId);
	}
}
