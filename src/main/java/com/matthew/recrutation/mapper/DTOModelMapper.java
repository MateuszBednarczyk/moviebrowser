package com.matthew.recrutation.mapper;

import com.matthew.recrutation.domain.MovieEntity;
import com.matthew.recrutation.dto.movie.MovieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DTOModelMapper {

    MovieDTO mapMovieEntityToMovieDTO(MovieEntity movieEntity);
}
