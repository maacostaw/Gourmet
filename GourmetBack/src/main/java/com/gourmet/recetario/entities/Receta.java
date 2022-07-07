package com.gourmet.recetario.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gourmet.recetario.enums.GrupoAlimenticio;
import com.gourmet.recetario.enums.Perfil;
import com.gourmet.recetario.enums.UnidadDeMedida;

@Entity
@Table(name = "recetas")
public class Receta implements Comparable<Receta>{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String nombre;
	@Column
	private String imagen;
	@OneToMany(targetEntity = IngredienteAbstract.class,fetch = FetchType.EAGER)
	private List<IngredienteAbstract> ingredientes;
	
	public Receta() {
		
	}
	
	public Receta(String nombre, String imagen) {
		this.nombre = nombre;
		this.imagen = imagen;
		this.ingredientes = new ArrayList<IngredienteAbstract>();		
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void addIngredienteNecesario(int medida,
			UnidadDeMedida unidadDeMedida,
			Alimento alimento) throws Exception {
		this.ingredientes.add(new IngredienteNecesario(medida,unidadDeMedida,alimento));
	}
	
	public void addIngredienteOpcional(Alimento alimento){
		this.ingredientes.add(new IngredienteOpcional(alimento));
	}
	
	/**
	 * Retorna el ingrediente con el nombre dado
	 * @param nombre del ingrediente
	 * @return ingrediente buscado, null en caso de que no este en la receta
	 */
	public IngredienteAbstract getIngrediente(String nombre) {
		for(IngredienteAbstract ingrediente: this.ingredientes) {
			if(ingrediente.getAlimento().getNombre().equals(nombre)){
				return ingrediente;
			}
		}
		return null;
	}
	
	public List<IngredienteAbstract> getIngredientes(){
		return ingredientes;
	}	
	
	public void setIngredientes(List<IngredienteAbstract> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public int calcularCalorias() {
		int calculo = 0;
		for(IngredienteAbstract ingrediente : this.ingredientes) {
			calculo += ingrediente.calcularCalorias();
		}
		return calculo;
	}
	
	public int numeroDeIngredientes() {
		return this.ingredientes.size();
	}
	
	public boolean hayGrupoAlimenticio(GrupoAlimenticio grupoAlimenticio) {
		for(IngredienteAbstract ingrediente : this.ingredientes) {
			if(ingrediente.getAlimento().getGrupoAlimenticio() == grupoAlimenticio) {
				return true;
			}
		}
		return false;
	}
	
	public boolean cumpleElPerfil(Perfil perfil) {
		return perfil.evaluarPerfil(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		} if(obj == this) {
			return true;
		}
		return this.nombre.equals(((Receta) obj).getNombre());
	}

	@Override
	public int compareTo(Receta receta) {
		return this.nombre.compareTo(receta.getNombre());
	}
	
	@Override
	public String toString() {
		String text = this.nombre + "\n";
		for(IngredienteAbstract ingrediente : this.ingredientes) {
			text += "\t" + ingrediente.toString() + "\n";
		}
		return text;
	}
}