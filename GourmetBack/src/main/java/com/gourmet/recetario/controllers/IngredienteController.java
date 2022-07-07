package com.gourmet.recetario.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
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

import com.gourmet.recetario.dtos.IngredienteAbstractDTO;
import com.gourmet.recetario.dtos.IngredienteNecesarioDTO;
import com.gourmet.recetario.dtos.IngredienteOpcionalDTO;
import com.gourmet.recetario.entities.IngredienteAbstract;
import com.gourmet.recetario.entities.IngredienteNecesario;
import com.gourmet.recetario.entities.IngredienteOpcional;
import com.gourmet.recetario.service.IngredienteService;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public IngredienteAbstractDTO crearIngrediente(@RequestBody IngredienteAbstractDTO ingredienteDTO) throws Exception{
		IngredienteAbstract ingrediente = ingredienteService.addIngrediente(modelMapper.map(ingredienteDTO, IngredienteAbstract.class));
		return modelMapper.map(ingrediente, IngredienteAbstractDTO.class);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<IngredienteAbstractDTO> encontrarIngredientes(){
		List<IngredienteAbstractDTO> ingredientesDTO = new ArrayList<IngredienteAbstractDTO>();
		for(IngredienteAbstract i : ingredienteService.getIngredientes()) {
			if(i.getClass() == IngredienteNecesario.class) {
				ingredientesDTO.add(modelMapper.map(i, IngredienteNecesarioDTO.class));
			}
			if(i.getClass() == IngredienteOpcional.class) {
				ingredientesDTO.add(modelMapper.map(i, IngredienteOpcionalDTO.class));
			}
		}
		return ingredientesDTO;
	}
	
	@GetMapping(value="/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public IngredienteAbstractDTO encontrarIngrediente(@PathVariable("id") Long id) throws EntityNotFoundException{
		IngredienteAbstract ingrediente = ingredienteService.getIngrediente(id);
		IngredienteAbstractDTO ingredienteDTO = modelMapper.map(ingrediente, IngredienteAbstractDTO.class);
		if(ingrediente.getClass() == IngredienteNecesario.class) {
			ingredienteDTO = modelMapper.map(ingrediente, IngredienteNecesarioDTO.class);
		}
		if(ingrediente.getClass() == IngredienteOpcional.class) {
			ingredienteDTO = modelMapper.map(ingrediente, IngredienteOpcionalDTO.class);
		}
		return ingredienteDTO;
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public IngredienteAbstractDTO actualizarIngrediente(@PathVariable("id") Long id, @RequestBody IngredienteAbstractDTO ingredienteDTO) throws EntityNotFoundException{
		IngredienteAbstract ingrediente = ingredienteService.updateIngrediente(id, modelMapper.map(ingredienteDTO, IngredienteAbstract.class));
		return modelMapper.map(ingrediente, IngredienteAbstractDTO.class);
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminarIngrediente(@PathVariable("id") Long id)  throws EntityNotFoundException{
		ingredienteService.deleteIngrediente(id);
	}
}
