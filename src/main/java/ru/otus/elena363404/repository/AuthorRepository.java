package ru.otus.elena363404.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.elena363404.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {

  Optional<Author> findById(String id);

  List<Author> findAll();
}
