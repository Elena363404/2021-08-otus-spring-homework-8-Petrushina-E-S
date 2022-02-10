package ru.otus.elena363404.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.elena363404.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String>, AuthorRepositoryCustom {

}
