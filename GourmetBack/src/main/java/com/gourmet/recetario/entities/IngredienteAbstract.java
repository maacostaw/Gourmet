package com.gourmet.recetario.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class IngredienteAbstract {
	@Id 
	@GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "alimento_id")
	protected Alimento alimento;
	
	public IngredienteAbstract() {
		
	}
	
	public IngredienteAbstract(Alimento alimento) {
		this.alimento = alimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Alimento getAlimento(){
		return this.alimento;
	}
	
	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}
	
	@Override
	public boolean equals(Object alimento) {
		if(alimento == null) {
			return false;
		} if(alimento == this) {
			return true;
		}
		return this.alimento.equals(alimento);
	}

	public abstract int calcularCalorias();
}
