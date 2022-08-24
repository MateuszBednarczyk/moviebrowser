package com.matthew.recrutation.api;

import com.matthew.recrutation.dto.movie.AddMovieAsFavouriteRequestDTO;
import com.matthew.recrutation.dto.movie.MovieDTO;
import com.matthew.recrutation.service.movie.MovieFindingService;
import com.matthew.recrutation.service.movie.MovieManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RequestMapping("/api/v1/movies")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
@Slf4j
public class MovieController {

    private final MovieFindingService movieFindingService;
    private final MovieManagementService movieManagementService;

    @GetMapping()
    public ResponseEntity<MovieDTO> findMovieByName(@RequestParam String movieTitle, @RequestParam String released) {
        return new ResponseEntity<>(movieFindingService.findByTitleAndReleaseYear(movieTitle, released), HttpStatus.OK);
    }

    @PostMapping("add-as-favourite")
    public ResponseEntity<String> addMovieAsFavourite(@RequestBody AddMovieAsFavouriteRequestDTO requestDTO, Principal principal) {
        movieManagementService.addMovieAsFavourite(requestDTO, principal);

        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    @GetMapping("top-liked-movies")
    public ResponseEntity<List<MovieDTO>> findMostLikedMovies() {
        return new ResponseEntity<>(movieFindingService.findMostLikedMovies(), HttpStatus.OK);
    }

    @GetMapping("liked-movies")
    public ResponseEntity<List<MovieDTO>> findUsersLikedMovies(Principal loggedUser) {
        return new ResponseEntity<>(movieFindingService.findUsersLikedMovies(loggedUser), HttpStatus.OK);
    }
}
