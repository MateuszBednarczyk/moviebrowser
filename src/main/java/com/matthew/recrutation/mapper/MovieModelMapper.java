package com.matthew.recrutation.mapper;

import com.matthew.recrutation.domain.MovieEntity;
import com.matthew.recrutation.model.MovieModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieModelMapper {
    MovieEntity mapMovieModelToMovieEntity(MovieModel movieModel);
}
