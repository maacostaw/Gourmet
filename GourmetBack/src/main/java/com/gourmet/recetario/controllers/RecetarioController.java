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

import com.gourmet.recetario.dtos.RecetarioDTO;
import com.gourmet.recetario.dtos.RecetarioDetailDTO;
import com.gourmet.recetario.entities.Recetario;
import com.gourmet.recetario.service.RecetarioService;

@RestController
@RequestMapping("/recetarios")
public class RecetarioController {
	@Autowired
	private RecetarioService recetarioService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RecetarioDTO crearRecetario(@RequestBody RecetarioDTO recetarioDTO) throws Exception{
		Recetario recetario = recetarioService.addRecetario(modelMapper.map(recetarioDTO, Recetario.class));
		return modelMapper.map(recetario, RecetarioDTO.class);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<RecetarioDetailDTO> encontrarRecetarios(){
		List<Recetario> recetarios = recetarioService.getRecetas();
		return modelMapper.map(recetarios, new TypeToken<List<RecetarioDetailDTO>>() {}.getType());
	}
	
	@GetMapping(value="/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public RecetarioDetailDTO encontrarRecetario(@PathVariable("id") Long id) throws EntityNotFoundException{
		Recetario recetario = recetarioService.getRecetario(id);
		return modelMapper.map(recetario, RecetarioDetailDTO.class);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public RecetarioDTO actualizarRecetario(@PathVariable("id") Long id, @RequestBody RecetarioDTO recetarioDTO) throws EntityNotFoundException{
		Recetario recetario = recetarioService.updateRecetario(id, modelMapper.map(recetarioDTO, Recetario.class));
		return modelMapper.map(recetario, RecetarioDTO.class);
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminarRecetario(@PathVariable("id") Long id)  throws EntityNotFoundException{
		recetarioService.deleteRecetario(id);
	}
}
