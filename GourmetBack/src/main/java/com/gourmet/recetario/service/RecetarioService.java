package com.gourmet.recetario.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gourmet.recetario.entities.Recetario;
import com.gourmet.recetario.enums.ErrorMessage;
import com.gourmet.recetario.repository.RecetarioRepository;

@Service
public class RecetarioService {
	@Autowired
	RecetarioRepository recetarioRepository;
	
	/**
	 * Añade un grupo de recetarios a la base de datos
	 * 
	 * @param recetas Lista de recetarios a crear
	 * @return Lista de recetarios ya creados en la base de datos
	 */
	@Transactional
	public List<Recetario> addRecetarios(List<Recetario> recetarios) {
		return recetarioRepository.saveAll(recetarios);
	}
	
	/**
	 * Se encarga de crear un recetario en la base de datos
	 * 
	 * @param recetario Entity de tipo Recetario a crear
	 * @return Objeto Entity de tipo recetario creado
	 */
	@Transactional
	public Recetario addRecetario(Recetario recetario) {
		return recetarioRepository.save(recetario);
	}
	
	/**
	 * Retorna todos los recetarios existentes en la bd
	 * 
	 * @return recetarios en la bd
	 */
	@Transactional
	public List<Recetario> getRecetas(){
		return recetarioRepository.findAll();
	}
	
	/**
	 * Obtiene los datos de un recetario a partir de su id
	 * 
	 * @param id Identifier del recetario
	 * @return Recetario buscada
	 * @throws EntityNotFoundException en caso de no encontrar el recetario
	 */
	@Transactional
	public Recetario getRecetario(Long id) throws EntityNotFoundException{
		Optional<Recetario> recetariosEncontrados = recetarioRepository.findById(id);
		if(recetariosEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETARIO_NO_ENCONTRADO.getMensaje());
		}
		return recetariosEncontrados.get();
	}
	
	/**
	 * Actualiza el recetario con el id dado
	 * 
	 * @param id Identifier del recetario
	 * @param recetario Nuevo recetario con los datos a cambiar
	 * @return Recetario cambiado
	 * @throws EntityNotFoundException en caso de no encontrar el recetario
	 */
	@Transactional
	public Recetario updateRecetario(Long id, Recetario recetario) throws EntityNotFoundException{
		Optional<Recetario> recetariosEncontrados = recetarioRepository.findById(id);
		if(recetariosEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETARIO_NO_ENCONTRADO.getMensaje());
		}
		recetario.setId(recetariosEncontrados.get().getId());
		return recetarioRepository.save(recetario);
	}
	
	/**
	 * Elimina el recetario con id dado
	 * 
	 * @param id Identifier del recetario
	 * @throws EntityNotFoundException en caso de no encontrar el recetario
	 */
	@Transactional
	public void deleteRecetario(Long id) throws EntityNotFoundException{
		Optional<Recetario> recetariosEncontrados = recetarioRepository.findById(id);
		if(recetariosEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETARIO_NO_ENCONTRADO.getMensaje());
		}
		Recetario recetarioEncontrado = recetariosEncontrados.get();
		recetarioRepository.delete(recetarioEncontrado);
	}
}
