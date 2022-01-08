package ru.otus.elena363404.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.elena363404.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {

  Optional<Genre> findById(String id);

  List<Genre> findAll();
}
