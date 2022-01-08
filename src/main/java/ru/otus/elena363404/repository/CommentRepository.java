package ru.otus.elena363404.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

  List<Comment> findByBook(@Param("book") Book book);
}
