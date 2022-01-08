package ru.otus.elena363404.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.elena363404.domain.Book;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
}
