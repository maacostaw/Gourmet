package com.gourmet.recetario.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gourmet.comunicacion.Publicador;

@Entity
@Table(name = "recetarios")
public class Recetario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String nombre;
	@Column
	private String imagen;
	@ManyToMany
	private List<Receta> recetas;
	@Transient
	private Publicador publicador;
	
	public Recetario() {
		
	}
	
	public Recetario(String nombre, String imagen) {
		this.nombre = nombre;
		this.imagen = imagen;
		this.recetas = new ArrayList<Receta>();
		this.publicador = new Publicador();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Receta> getRecetas(){
		return recetas;
	}
	
	public Receta addReceta(Receta receta) {
		this.recetas.add(receta);
		this.publicador.notificar(receta, this);
		return receta;
	}
	
	/**
	 * Retorna la receta con el nombre dado
	 * @param nombre de la receta
	 * @return receta buscada, null en caso de que no este en el recetario
	 */
	public Receta getReceta(String nombre) {
		for(Receta receta: this.recetas) {
			if(receta.getNombre().equals(nombre)) {
				return receta;
			}
		}
		return null;
	}
	
	public int getCantidadRecetas() {
		return this.recetas.size();
	}

	public Publicador getPublicador() {
		return publicador;
	}
	
	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}

	public void setPublicador(Publicador publicador) {
		this.publicador = publicador;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		} if(obj == this) {
			return true;
		}
		return this.nombre.equals(((Recetario) obj).getNombre());
	}
	
	@Override
	public String toString() {
		String text = this.nombre + "\n";
		for(Receta receta : this.recetas) {
			text += "\t" + receta.toString() + "\n";
		}
		return text;
	}
}
