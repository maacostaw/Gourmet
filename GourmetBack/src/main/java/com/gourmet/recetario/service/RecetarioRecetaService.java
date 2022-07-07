package com.gourmet.recetario.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gourmet.recetario.entities.Receta;
import com.gourmet.recetario.entities.Recetario;
import com.gourmet.recetario.enums.ErrorMessage;
import com.gourmet.recetario.repository.RecetaRepository;
import com.gourmet.recetario.repository.RecetarioRepository;

@Service
public class RecetarioRecetaService {
	
	@Autowired
	private RecetaRepository recetaRepository;

	@Autowired
	private RecetarioRepository recetarioRepository;
	
	/**
	 * Le agrega una receta al recetario solicitado
	 * 
	 * @param recetarioId Identifier del recetario al que se le agregara la receta
	 * @param recetaId Identifier de la receta
	 * @return recetario actualizado
	 * @throws EntityNotFoundException en caso de no encontrar o el recetario o la receta
	 */
	@Transactional
	public Recetario addReceta(Long recetarioId, Long recetaId) throws EntityNotFoundException {
		Optional<Recetario> recetariosEncontrados = recetarioRepository.findById(recetarioId);
		if(recetariosEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETARIO_NO_ENCONTRADO.getMensaje());
		}
		Optional<Receta> recetasEncontradas = recetaRepository.findById(recetaId);
		if(recetasEncontradas.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETA_NO_ENCONTRADA.getMensaje());
		}
		Recetario recetario = recetariosEncontrados.get();
		Receta receta = recetasEncontradas.get();
		recetario.getRecetas().add(receta);
		return recetario;
	}
	
	/**
	 * Obtiene una lista de recetass para el recetario dada¿o
	 * 
	 * @param recetarioNombre Recetario al que le queremos sacar las recetas
	 * @return Lista de recetas
	 */
	@Transactional
	public List<Receta> getRecetas(Long recetarioId) throws EntityNotFoundException{
		Optional<Recetario> recetariosEncontrados = recetarioRepository.findById(recetarioId);
		if(recetariosEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETARIO_NO_ENCONTRADO.getMensaje());
		}
		Recetario recetario = recetariosEncontrados.get();
		return recetario.getRecetas();
	}
	
	/**
	 * Obtiene una receta asociada a un recetario
	 * 
	 * @param recetarioId Identifier del recetario al que se le agregara la receta
	 * @param recetaId Identifier de la receta
	 * @return Receta buscada
	 * @throws EntityNotFoundException Si la receta o el recetario no existen, o en caso 
	 * de que los dos no esten relacionados
	 */
	@Transactional
	public Receta getReceta(Long recetarioId, Long recetaId) throws EntityNotFoundException{
		Optional<Recetario> recetariosEncontrados = recetarioRepository.findById(recetarioId);
		if(recetariosEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETARIO_NO_ENCONTRADO.getMensaje());
		}
		Optional<Receta> recetasEncontradas = recetaRepository.findById(recetaId);
		if(recetasEncontradas.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETA_NO_ENCONTRADA.getMensaje());
		}
		Recetario recetario = recetariosEncontrados.get();
		Receta receta = recetasEncontradas.get();
		if(recetario.getRecetas().contains(receta)) {
			return receta;
		}
		throw new EntityNotFoundException(ErrorMessage.NO_RELACION_RECETARIO_RECETA.getMensaje());
	}
	
	/**
	 * Desasocia una receta asociada a un recetario
	 * 
	 * @param recetarioId Identifier del recetario al que se le agregara la receta
	 * @param recetaId Identifier de la receta
	 * @return Receta buscada
	 * @throws EntityNotFoundException Si la receta o el recetario no existen, o en caso 
	 * de que los dos no esten relacionados
	 */
	@Transactional
	public void removeReceta(Long recetarioId, Long recetaId) throws EntityNotFoundException{
		Optional<Recetario> recetariosEncontrados = recetarioRepository.findById(recetarioId);
		if(recetariosEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETARIO_NO_ENCONTRADO.getMensaje());
		}
		Optional<Receta> recetasEncontradas = recetaRepository.findById(recetaId);
		if(recetasEncontradas.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.RECETA_NO_ENCONTRADA.getMensaje());
		}
		Recetario recetario = recetariosEncontrados.get();
		Receta receta = recetasEncontradas.get();
		if(recetario.getRecetas().contains(receta)) {
			recetario.getRecetas().remove(receta);
		}
		throw new EntityNotFoundException(ErrorMessage.NO_RELACION_RECETARIO_RECETA.getMensaje());
	}
}
