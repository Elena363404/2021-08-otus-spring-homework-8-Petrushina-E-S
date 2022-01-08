package ru.otus.elena363404.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.elena363404.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String>, GenreRepositoryCustom {
}
