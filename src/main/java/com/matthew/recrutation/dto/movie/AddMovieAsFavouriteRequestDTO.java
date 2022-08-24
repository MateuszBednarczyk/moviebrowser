package com.matthew.recrutation.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Immutable
@Getter
@AllArgsConstructor
public class AddMovieAsFavouriteRequestDTO {
    String movieTitle;
    String released;
}
