package com.gourmet.recetario.controllers;

import org.springframework.http.HttpStatus;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gourmet.recetario.dtos.AlimentoDTO;
import com.gourmet.recetario.entities.Alimento;
import com.gourmet.recetario.service.AlimentoService;

@RestController
@RequestMapping("/alimentos")
public class AlimentoController {
	
	@Autowired
	private AlimentoService alimentoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AlimentoDTO crearAlimento(@RequestBody AlimentoDTO alimentoDTO) throws Exception{
		Alimento alimento = alimentoService.addAlimento(modelMapper.map(alimentoDTO, Alimento.class));
		return modelMapper.map(alimento, AlimentoDTO.class);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<AlimentoDTO> encontrarAlimentos(){
		List<Alimento> alimentos = alimentoService.getAlimentos();
		return modelMapper.map(alimentos, new TypeToken<List<AlimentoDTO>>() {}.getType());
	}
	
	@GetMapping(value="/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public AlimentoDTO encontrarAlimento(@PathVariable("id") Long id) throws EntityNotFoundException{
		Alimento alimento = alimentoService.getAlimento(id);
		return modelMapper.map(alimento, AlimentoDTO.class);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public AlimentoDTO actualizarAlimento(@PathVariable("id") Long id, @RequestBody AlimentoDTO alimentoDTO) throws EntityNotFoundException{
		Alimento alimento = alimentoService.updateAlimento(id, modelMapper.map(alimentoDTO, Alimento.class));
		return modelMapper.map(alimento, AlimentoDTO.class);
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminarAlimento(@PathVariable("id") Long id)  throws EntityNotFoundException{
		alimentoService.deleteAlimento(id);
	}
}
