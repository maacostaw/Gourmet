package com.gourmet.recetario.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gourmet.recetario.service.RecetarioRecetaService;
import com.gourmet.recetario.dtos.RecetaDetailDTO;
import com.gourmet.recetario.entities.Receta;

@RestController
@RequestMapping("/recetarios")
public class RecetarioRecetaController {
	
	@Autowired
	private RecetarioRecetaService recetarioRecetaService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{recetarioId}/recetas")
	@ResponseStatus(code = HttpStatus.OK)
	public List<RecetaDetailDTO> encontrarRecetas(@PathVariable("recetarioId") Long recetarioId) throws EntityNotFoundException{
		List<Receta> recetas = recetarioRecetaService.getRecetas(recetarioId);
		return modelMapper.map(recetas, new TypeToken<List<RecetaDetailDTO>>() {}.getType());
	}
}
