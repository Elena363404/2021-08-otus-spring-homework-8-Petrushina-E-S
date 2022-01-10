package ru.otus.elena363404.withlisteners;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.repository.AuthorRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("AuthorRepository with listener in context")
public class AuthorRepositoryWithListenerTest{

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private MongoOperations operations;

  @DisplayName("When deleting Author should remove it from the Book")
  @Test
  void shouldRemoveAuthorFromBookWhenRemovingAuthor() {

    val books = operations.findAll(Book.class);
    val book = books.get(0);
    val author = book.getAuthor();

    // delete author
    authorRepository.delete(author);

    // load a book and checking its author
    val actualBookOptional = operations.findById(book.getId(), Book.class);

    assertThat( actualBookOptional != null && actualBookOptional.getAuthor() == null);
  }

}
