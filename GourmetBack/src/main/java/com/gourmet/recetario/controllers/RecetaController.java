package com.gourmet.recetario.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gourmet.recetario.dtos.RecetaDTO;
import com.gourmet.recetario.dtos.RecetaDetailDTO;
import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.service.RecetaService;

@RestController
@RequestMapping("/recetas")
public class RecetaController {
	@Autowired
	private RecetaService recetaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RecetaDTO crearReceta(@RequestBody RecetaDTO recetaDTO) throws Exception{
		Receta receta = recetaService.addReceta(modelMapper.map(recetaDTO, Receta.class));
		return modelMapper.map(receta, RecetaDTO.class);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<RecetaDetailDTO> encontrarRecetas(){
		List<Receta> recetas = recetaService.getRecetas();
		return modelMapper.map(recetas, new TypeToken<List<RecetaDetailDTO>>() {}.getType());
	}
	
	@GetMapping(value="/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public RecetaDetailDTO encontrarReceta(@PathVariable("id") Long id) throws EntityNotFoundException{
		Receta receta = recetaService.getReceta(id);
		return modelMapper.map(receta, RecetaDetailDTO.class);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public RecetaDTO actualizarReceta(@PathVariable("id") Long id, @RequestBody RecetaDTO recetaDTO) throws EntityNotFoundException{
		Receta receta = recetaService.updateReceta(id, modelMapper.map(recetaDTO, Receta.class));
		return modelMapper.map(receta, RecetaDTO.class);
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminarReceta(@PathVariable("id") Long id)  throws EntityNotFoundException{
		recetaService.deleteReceta(id);
	}
}
