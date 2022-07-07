package com.gourmet.recetario.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Stream;

@Converter
public class GrupoAlimenticioConverter implements AttributeConverter<GrupoAlimenticio, String> {
	
	@Override
    public String convertToDatabaseColumn(GrupoAlimenticio grupoAlimenticio) {
        if (grupoAlimenticio == null) {
            return null;
        }
        return grupoAlimenticio.getNombre();
    }

    @Override
    public GrupoAlimenticio convertToEntityAttribute(String nombre) {
        if (nombre == null) {
            return null;
        }

        return Stream.of(GrupoAlimenticio.values())
          .filter(grupoAlimenticio -> grupoAlimenticio.getNombre().equals(nombre))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}
