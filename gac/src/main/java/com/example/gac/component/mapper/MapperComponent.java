package com.example.gac.component.mapper;

import java.util.List;
import java.util.Optional;

// Interfaz que después cuyos métodos se van a sobrescribir después para cada entidad.
public interface MapperComponent<T, S> {
    /**
     * Función que mapea un objeto de tipo S (DAO) a un objeto de tipo P (DTO).
     * @param elementS
     * @return elemento tipo T
     */
    Optional<T> mapDaoToDto(S elementS);

    /**
     * Función que mapea un objeto de tipo T (DTO) a un objeto de tipo S (DAO).
     * @param elementT
     * @return elemento tipo S
     */
    Optional<S> mapDtoToDao(T elementT);

    /**
     * Mapea una lista de elementos del tipo S a una lista de elementos del tipo T.
     * @param elementS
     * @return
     */
    List<T> mapDaoToDto(List<S> elementS);

    /**
     * Mapea una lista de elementos del tipo T a una lista de elementos del tipo S.
     * @param elementT
     * @return
     */
    List<S> mapDtoToDao(List<T> elementT);
}
