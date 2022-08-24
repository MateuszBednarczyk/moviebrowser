package com.matthew.recrutation.repository;

import com.matthew.recrutation.domain.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    Optional<MovieEntity> findByTitle(String title);

    @Query(value = "SELECT m FROM MovieEntity as m order by m.likes DESC")
    List<MovieEntity> findAllAndOrderByLikes();
}
