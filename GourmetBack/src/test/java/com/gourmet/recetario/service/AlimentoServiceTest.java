package com.gourmet.recetario.service;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gourmet.App;
import com.gourmet.recetario.entities.Alimento;
import com.gourmet.recetario.enums.GrupoAlimenticio;
import com.gourmet.recetario.enums.UnidadDeMedida;

@Transactional
@RunWith(SpringRunner.class) 
@SpringBootTest(classes=App.class)
public class AlimentoServiceTest {
	@Autowired
	private AlimentoService alimentoService;
	
	@Test
	public void when_addAlimento_isCalled_with_AlimentoNuevo_t_CantidadDeAlimentosAumentaEn1() {
		int alimentosIniciales = alimentoService.getAlimentos().size();
		Alimento alimento = new Alimento("prueba",50,100,UnidadDeMedida.GRAMOS,GrupoAlimenticio.CARNES);
		alimentoService.addAlimento(alimento);
		assertEquals(alimentoService.getAlimentos().size(), alimentosIniciales+1);
	}
}
