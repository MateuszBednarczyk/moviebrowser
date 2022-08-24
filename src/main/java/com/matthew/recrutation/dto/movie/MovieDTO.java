package com.matthew.recrutation.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Immutable
@Getter
@AllArgsConstructor
public class MovieDTO {

    private String title;
    private String plot;
    private String genre;
    private String director;
    private String poster;
    private String released;

}
