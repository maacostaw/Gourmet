package com.gourmet.recetario.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.enums.ErrorMessage;
import com.gourmet.recetario.repository.RecetaRepository;

@Service
public class RecetaService {
	@Autowired
	RecetaRepository recetaRepository;
	
	/**
	 * Añade un grupo de recetas a la base de datos
	 * 
	 * @param recetas Lista de recetas a crear
	 * @return Lista de recetas ya creadas en la base de datos
	 */
	@Transactional
	public List<Receta> addRecetas(List<Receta> recetas) {
		return recetaRepository.saveAll(recetas);
	}
	
	/**
	 * Se encarga de crear un receta en la base de datos
	 * 
	 * @param receta Entity de tipo Receta a crear
	 * @return Objeto Entity de tipo receta creada
	 */
	@Transactional
	public Receta addReceta(Receta receta) {
		return recetaRepository.save(receta);
	}
	
	/**
	 * Retorna todas las recetas existentes en la bd
	 * 
	 * @return recetas en la bd
	 */
	@Transactional
	public List<Receta> getRecetas(){
		return recetaRepository.findAll();
	}
	
	/**
	 * Obtiene los datos de una receta a partir de su id
	 * 
	 * @param id Identifier de la receta
	 * @return Receta buscada
	 * @throws EntityNotFoundException en caso de no encontrar la receta
	 */
	@Transactional
	public Receta getReceta(Long id) throws EntityNotFoundException{
		Optional<Receta> recetasEncontradas = recetaRepository.findById(id);
		if(recetasEncontradas.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETA_NO_ENCONTRADA.getMensaje());
		}
		return recetasEncontradas.get();
	}
	
	/**
	 * Actualiza la receta con el id dado
	 * 
	 * @param id Identifier de la receta dada
	 * @param receta Nueva receta con los datos a cambiar
	 * @return Receta cambiada
	 * @throws EntityNotFoundException en caso de no encontrar la receta
	 */
	@Transactional
	public Receta updateReceta(Long id, Receta receta) throws EntityNotFoundException{
		Optional<Receta> recetasEncontradas = recetaRepository.findById(id);
		if(recetasEncontradas.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETA_NO_ENCONTRADA.getMensaje());
		}
		receta.setId(recetasEncontradas.get().getId());
		return recetaRepository.save(receta);
	}
	
	/**
	 * Elimina la receta con el id dado
	 * 
	 * @param id Identifier de la receta
	 * @throws EntityNotFoundException en caso de no encontrar la receta
	 */
	@Transactional
	public void deleteReceta(Long id) throws EntityNotFoundException{
		Optional<Receta> recetasEncontradas = recetaRepository.findById(id);
		if(recetasEncontradas.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETA_NO_ENCONTRADA.getMensaje());
		}
		Receta recetaEncontrada = recetasEncontradas.get();
		recetaRepository.delete(recetaEncontrada);
	}
}
