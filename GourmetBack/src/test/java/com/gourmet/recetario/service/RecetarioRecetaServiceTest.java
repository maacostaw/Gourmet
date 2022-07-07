package com.gourmet.recetario.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gourmet.App;
import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;

@Transactional
@RunWith(SpringRunner.class) 
@SpringBootTest(classes=App.class)
public class RecetarioRecetaServiceTest {
	@Autowired
	RecetarioRecetaService recetarioRecetaService;
	
	@Autowired
	RecetarioService recetarioService;
	
	@Autowired
	RecetaService recetaService;
	
	@Test
	public void when_addReceta_isCalled_RecetaAñadidaARecetario() throws EntityNotFoundException{
		Recetario malteadas = recetarioService.addRecetario(new Recetario("Recetario de malteadas",""));
		Receta malteada = recetaService.addReceta(new Receta("Malteada de vainilla",""));

		recetarioRecetaService.addReceta(malteadas.getId(),malteada.getId());
		
		Receta malteadaBuscada = recetarioRecetaService.getReceta(malteadas.getId(),malteada.getId());
		
		boolean sameId = malteada.getId().equals(malteadaBuscada.getId());
		boolean sameNombre = malteada.getNombre().equals(malteadaBuscada.getNombre());
		
		boolean sameProperties = sameId && sameNombre;
		
        assertTrue(sameProperties);
	}
	
	@Test(expected = Exception.class)
	public void when_getReceta_isCalled_and_RecetaNoRelacionada_throwError() throws EntityNotFoundException{
		Recetario malteadas = recetarioService.addRecetario(new Recetario("Recetario de malteadas",""));
		Receta malteada = recetaService.addReceta(new Receta("Malteada de vainilla",""));
		
		recetarioRecetaService.getReceta(malteadas.getId(),malteada.getId());
	}
	
	@Test(expected = Exception.class)
	public void when_removeReceta_isCalled_Entonces_RecetaNoRelacionada() throws EntityNotFoundException{
		Recetario malteadas = recetarioService.addRecetario(new Recetario("Recetario de malteadas",""));
		Receta malteada = recetaService.addReceta(new Receta("Malteada de vainilla",""));

		recetarioRecetaService.addReceta(malteadas.getId(),malteada.getId());
		
		assertNotNull(recetarioRecetaService.getReceta(malteadas.getId(),malteada.getId()));
	    
		recetarioRecetaService.removeReceta(malteadas.getId(),malteada.getId());
		
		recetarioRecetaService.getReceta(malteadas.getId(),malteada.getId());		
	}
}
