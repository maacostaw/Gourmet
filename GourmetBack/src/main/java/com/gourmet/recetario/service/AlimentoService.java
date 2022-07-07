package com.gourmet.recetario.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gourmet.recetario.entities.Alimento;
import com.gourmet.recetario.enums.ErrorMessage;
import com.gourmet.recetario.repository.AlimentoRepository;

@Service
public class AlimentoService {
	
	@Autowired
	private AlimentoRepository alimentoRepository;
	
	/**
	 * Añade un grupo de alimentos a la base de datos
	 * 
	 * @param alimentos Lista de alimentos a crear
	 * @return Lista de alimentos ya creados en la base de datos
	 */
	@Transactional
	public List<Alimento> addAlimentos(List<Alimento> alimentos) {
		return alimentoRepository.saveAll(alimentos);
	}
	
	/**
	 * Se encarga de crear un alimento en la base de datos
	 * 
	 * @param alimento Entity de tipo Alimento a crear
	 * @return Objeto Entity de tipo alimento creado
	 */
	@Transactional
	public Alimento addAlimento(Alimento alimento) {
		return alimentoRepository.save(alimento);
	}
	
	/**
	 * Retorna todos los alimentos existentes en la bd
	 * 
	 * @return alimentos en la bd
	 */
	@Transactional
	public List<Alimento> getAlimentos(){
		return alimentoRepository.findAll();
	}
	
	/**
	 * Obtiene los datos de un alimento a partir de su id
	 * 
	 * @param id Identifier del alimento
	 * @return Alimento buscado
	 * @throws EntityNotFoundException en caso de no encontrar el alimento
	 */
	@Transactional
	public Alimento getAlimento(long id) throws EntityNotFoundException{
		Optional<Alimento> alimentosEncontrados = alimentoRepository.findById(id);
		if(alimentosEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.ALIMENTO_NO_ENCONTRADO.getMensaje());
		}
		return alimentosEncontrados.get();
	}
	
	/**
	 * Actualiza el alimento con el id dado 
	 * 
	 * @param id Identifier del alimento
	 * @param alimento Nuevo alimento con los datos a cambiar
	 * @return Alimento cambiado
	 * @throws EntityNotFoundException en caso de no encontrar el alimento
	 */
	@Transactional
	public Alimento updateAlimento(Long id, Alimento alimento) throws EntityNotFoundException{
		Optional<Alimento> alimentosEncontrados = alimentoRepository.findById(id);
		if(alimentosEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.ALIMENTO_NO_ENCONTRADO.getMensaje());
		}
		alimento.setId(alimentosEncontrados.get().getId());
		return alimentoRepository.save(alimento);
	}
	
	/**
	 * Elimina el alimento con el id dado
	 * 
	 * @param id Identifier del alimento
	 * @throws EntityNotFoundException en caso de no encontrar el alimento
	 */
	@Transactional
	public void deleteAlimento(Long id) throws EntityNotFoundException{
		Optional<Alimento> alimentosEncontrados = alimentoRepository.findById(id);
		if(alimentosEncontrados.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.ALIMENTO_NO_ENCONTRADO.getMensaje());
		}
		Alimento alimentoEncontrado = alimentosEncontrados.get();
		alimentoRepository.delete(alimentoEncontrado);
	}
}
