package ru.otus.elena363404.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.elena363404.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

  Optional<Book> findById(String id);

  List<Book> findAll();

}
