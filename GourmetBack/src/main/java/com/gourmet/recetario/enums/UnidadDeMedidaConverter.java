package com.gourmet.recetario.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Stream;

@Converter
public class UnidadDeMedidaConverter implements AttributeConverter<UnidadDeMedida, String> {
	
	@Override
    public String convertToDatabaseColumn(UnidadDeMedida unidadDeMedida) {
        if (unidadDeMedida == null) {
            return null;
        }
        return unidadDeMedida.getNombre();
    }

    @Override
    public UnidadDeMedida convertToEntityAttribute(String nombre) {
        if (nombre == null) {
            return null;
        }

        return Stream.of(UnidadDeMedida.values())
          .filter(unidadDeMedida -> unidadDeMedida.getNombre().equals(nombre))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}