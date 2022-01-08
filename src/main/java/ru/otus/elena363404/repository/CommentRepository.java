package ru.otus.elena363404.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {

  Optional<Comment> findById(String id);

  List<Comment> findAll();

  List<Comment> findByBook(@Param("book") Book book);
}
