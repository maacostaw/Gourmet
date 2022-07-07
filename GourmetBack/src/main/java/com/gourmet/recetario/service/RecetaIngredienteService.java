package com.gourmet.recetario.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gourmet.recetario.entities.IngredienteAbstract;
import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.enums.ErrorMessage;
import com.gourmet.recetario.repository.IngredienteRepository;
import com.gourmet.recetario.repository.RecetaRepository;

@Service
public class RecetaIngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	private RecetaRepository recetaRepository;
	
	/**
	 * Le agrega un ingrediente a la receta solicitada
	 * 
	 * @param recetaId Id de la receta
	 * @param ingredienteId Id del ingrediente
	 * @return receta actualizada
	 * @throws EntityNotFoundException en caso de no encontrar o la receta o el ingrediente
	 */
	@Transactional
	public Receta addIngrediente(Long recetaId, Long ingredienteId) throws EntityNotFoundException {
		Optional<Receta> recetasEncontradas = recetaRepository.findById(recetaId);
		if(recetasEncontradas.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETA_NO_ENCONTRADA.getMensaje());
		}
		Optional<IngredienteAbstract> ingredientesEncontrados = ingredienteRepository.findById(ingredienteId);
		if(ingredientesEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.INGREDIENTE_NO_ENCONTRADO.getMensaje());
		}
		Receta receta = recetasEncontradas.get();
		receta.getIngredientes().add(ingredientesEncontrados.get());
		return receta;
	}
	
	/**
	 * Obtiene una lista de ingredientes para la receta dada
	 * 
	 * @param recetaNombre Receta a la que le queremos sacar los ingredientes
	 * @return Lista de ingredientes
	 */
	@Transactional
	public List<IngredienteAbstract> getIngredientes(Long recetaId) throws EntityNotFoundException{
		Optional<Receta> recetasEncontradas = recetaRepository.findById(recetaId);
		if(recetasEncontradas.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETA_NO_ENCONTRADA.getMensaje());
		}
		Receta receta = recetasEncontradas.get();
		return receta.getIngredientes();
	}
	
	/**
	 * Obtiene un ingrediente asociado a una receta
	 * 
	 * @param recetaId Id de la receta
	 * @param ingredienteId Id del ingrediente
	 * @return Ingrediente buscado
	 * @throws EntityNotFoundException Si el ingrediente o la receta no existen, o en caso
	 * de que los dos no esten asociados
	 */
	@Transactional
	public IngredienteAbstract getIngrediente(Long recetaId, Long ingredienteId) throws EntityNotFoundException{
		Optional<Receta> recetasEncontradas = recetaRepository.findById(recetaId);
		if(recetasEncontradas.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETA_NO_ENCONTRADA.getMensaje());
		}
		Optional<IngredienteAbstract> ingredientesEncontrados = ingredienteRepository.findById(ingredienteId);
		if(ingredientesEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.INGREDIENTE_NO_ENCONTRADO.getMensaje());
		}
		Receta receta = recetasEncontradas.get();
		IngredienteAbstract ingrediente = ingredientesEncontrados.get();
		if(receta.getIngredientes().contains(ingrediente)) {
			return ingrediente;
		}
		
		throw new EntityNotFoundException(ErrorMessage.NO_RELACION_RECETA_INGREDIENTE.getMensaje());
	}
	
	/**
	 * Desasocia un ingrediente asociado a una receta
	 * 
	 * @param recetaId Id de la receta
	 * @param ingredienteId Id del ingrediente
	 * @return Ingrediente buscado
	 * @throws EntityNotFoundException Si el ingrediente o la receta no existen, o en caso
	 * de que los dos no esten asociados
	 */
	@Transactional
	public void removeIngrediente(Long recetaId, Long ingredienteId) throws EntityNotFoundException{
		Optional<Receta> recetasEncontradas = recetaRepository.findById(recetaId);
		if(recetasEncontradas.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETA_NO_ENCONTRADA.getMensaje());
		}
		Optional<IngredienteAbstract> ingredientesEncontrados = ingredienteRepository.findById(ingredienteId);
		if(ingredientesEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.INGREDIENTE_NO_ENCONTRADO.getMensaje());
		}
		Receta receta = recetasEncontradas.get();
		IngredienteAbstract ingrediente = ingredientesEncontrados.get();
		if(receta.getIngredientes().contains(ingrediente)) {
			receta.getIngredientes().remove(ingrediente);
		}
		throw new EntityNotFoundException(ErrorMessage.NO_RELACION_RECETA_INGREDIENTE.getMensaje());

	}
}
