package com.gourmet.recetario.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gourmet.recetario.entities.IngredienteAbstract;
import com.gourmet.recetario.enums.ErrorMessage;
import com.gourmet.recetario.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	IngredienteRepository ingredienteRepository;
	
	/**
	 * Añade un grupo de ingredientes a la base de datos
	 * 
	 * @param ingredientes Lista de ingredientes a crear
	 * @return Lista de ingredientes ya creados en la base de datos
	 */
	@Transactional
	public List<IngredienteAbstract> addIngredientes(List<IngredienteAbstract> ingredientes) {
		return ingredienteRepository.saveAll(ingredientes);
	}
	
	/**
	 * Se encarga de crear un ingrediente en la base de datos
	 * 
	 * @param ingrediente Entity de tipo Ingrediente a crear
	 * @return Objeto Entity de tipo Ingrediente creado
	 */
	@Transactional
	public IngredienteAbstract addIngrediente(IngredienteAbstract ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}
	
	/**
	 * Retorna todos los ingredientes existentes en la bd
	 * 
	 * @return ingredientes en la bd
	 */
	@Transactional
	public List<IngredienteAbstract> getIngredientes(){
		return ingredienteRepository.findAll();
	}
	
	/**
	 * Obtiene los datos de un ingrediente a partir de su id
	 * 
	 * @param id del ingrediente
	 * @return Ingrediente buscado
	 * @throws EntityNotFoundException en caso de no encontrar el ingrediente
	 */
	@Transactional
	public IngredienteAbstract getIngrediente(Long id) throws EntityNotFoundException{
		Optional<IngredienteAbstract> ingredientesEncontrados = ingredienteRepository.findById(id);
		if(ingredientesEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.INGREDIENTE_NO_ENCONTRADO.getMensaje());
		}
		return ingredientesEncontrados.get();
	}
	
	/**
	 * Actualiza el ingrediente con el id dado
	 * 
	 * @param id Identificador del ingrediente a actualizar
	 * @param ingrediente Nuevo ingrediente con los datos a cambiar
	 * @return Ingrediente cambiado
	 * @throws EntityNotFoundException en caso de no encontrar el ingrediente
	 */
	@Transactional
	public IngredienteAbstract updateIngrediente(Long id, IngredienteAbstract ingrediente) throws EntityNotFoundException{
		Optional<IngredienteAbstract> ingredientesEncontrados = ingredienteRepository.findById(id);
		if(ingredientesEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.INGREDIENTE_NO_ENCONTRADO.getMensaje());
		}
		ingrediente.setId(ingredientesEncontrados.get().getId());
		return ingredienteRepository.save(ingrediente);
	}
	
	/**
	 * Elimina el ingrediente con el id dado
	 * 
	 * @param id del elemento a eliminar
	 * @throws EntityNotFoundException en caso de no encontrar el ingrediente
	 */
	@Transactional
	public void deleteIngrediente(Long id) throws EntityNotFoundException{
		Optional<IngredienteAbstract> ingredientesEncontrados = ingredienteRepository.findById(id);
		if(ingredientesEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.ALIMENTO_NO_ENCONTRADO.getMensaje());
		}
		ingredienteRepository.delete(ingredientesEncontrados.get());
	}
}
