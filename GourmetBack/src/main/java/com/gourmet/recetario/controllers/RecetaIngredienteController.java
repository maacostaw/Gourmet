package com.gourmet.recetario.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gourmet.recetario.dtos.IngredienteAbstractDTO;
import com.gourmet.recetario.dtos.IngredienteNecesarioDTO;
import com.gourmet.recetario.dtos.IngredienteOpcionalDTO;
import com.gourmet.recetario.entities.IngredienteAbstract;
import com.gourmet.recetario.entities.IngredienteNecesario;
import com.gourmet.recetario.entities.IngredienteOpcional;
import com.gourmet.recetario.service.RecetaIngredienteService;

@RestController
@RequestMapping("/recetas")
public class RecetaIngredienteController {
	
	@Autowired
	private RecetaIngredienteService recetaIngredienteService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{recetaId}/ingredientes")
	@ResponseStatus(code = HttpStatus.OK)
	public List<IngredienteAbstractDTO> encontrarIngredientes(@PathVariable("recetaId") Long recetaId) throws EntityNotFoundException{
		List<IngredienteAbstractDTO> ingredientesDTO = new ArrayList<IngredienteAbstractDTO>();
		for(IngredienteAbstract i : recetaIngredienteService.getIngredientes(recetaId)) {
			if(i.getClass() == IngredienteNecesario.class) {
				ingredientesDTO.add(modelMapper.map(i, IngredienteNecesarioDTO.class));
			}
			if(i.getClass() == IngredienteOpcional.class) {
				ingredientesDTO.add(modelMapper.map(i, IngredienteOpcionalDTO.class));
			}
		}
		return ingredientesDTO;
	}
}
