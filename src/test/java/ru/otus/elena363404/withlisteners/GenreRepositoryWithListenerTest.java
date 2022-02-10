package ru.otus.elena363404.withlisteners;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.repository.GenreRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("GenreRepository with listener in context")
public class GenreRepositoryWithListenerTest {

  @Autowired
  private GenreRepository genreRepository;

  @Autowired
  private MongoOperations operations;

  @DisplayName("When deleting Genre should remove it from the Book")
  @Test
  void shouldRemoveGenreFromBookWhenRemovingGenre() {

    val books = operations.findAll(Book.class);
    val book = books.get(0);
    val genre = book.getGenre();

    // delete genre
    genreRepository.delete(genre);

    // load a book and checking its genre
    Book actualBookOptional = operations.findById(book.getId(), Book.class);

    assertThat( actualBookOptional != null && actualBookOptional.getGenre() == null);
  }
}
