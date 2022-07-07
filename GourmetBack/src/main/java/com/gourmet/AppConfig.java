package com.gourmet;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.gourmet.recetario.dtos.IngredienteAbstractDTO;
import com.gourmet.recetario.dtos.IngredienteNecesarioDTO;
import com.gourmet.recetario.dtos.IngredienteOpcionalDTO;
import com.gourmet.recetario.dtos.RecetaDetailDTO;
import com.gourmet.recetario.entities.IngredienteAbstract;
import com.gourmet.recetario.entities.IngredienteNecesario;
import com.gourmet.recetario.entities.IngredienteOpcional;
import com.gourmet.recetario.entities.Receta;

@Configuration
@EntityScan("com.gourmet.recetario.entities")
@EnableJpaRepositories(basePackages = "com.gourmet.recetario.repository")
public class AppConfig {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		Converter<Receta, RecetaDetailDTO> recetaDetailConverter = new AbstractConverter<Receta, RecetaDetailDTO>() {
			protected RecetaDetailDTO convert(Receta source) {
				RecetaDetailDTO recetaDetailDTO = new RecetaDetailDTO();
				recetaDetailDTO.setId(source.getId());
				recetaDetailDTO.setImagen(source.getImagen());
				recetaDetailDTO.setNombre(source.getNombre());
				
				List<IngredienteAbstractDTO> ingredientesDTO = new ArrayList<IngredienteAbstractDTO>();
				for(IngredienteAbstract i : source.getIngredientes()) {
					if(i.getClass() == IngredienteNecesario.class) {
						ingredientesDTO.add(modelMapper.map(i, IngredienteNecesarioDTO.class));
					}
					if(i.getClass() == IngredienteOpcional.class) {
						ingredientesDTO.add(modelMapper.map(i, IngredienteOpcionalDTO.class));
					}
				}
				
				recetaDetailDTO.setIngredientes(ingredientesDTO);
				
				return recetaDetailDTO;
			}
		};
		
		modelMapper.addConverter(recetaDetailConverter);
		return modelMapper;
	}
}
