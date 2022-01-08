package ru.otus.elena363404.withlisteners;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.elena363404.repository.BookRepository;
import ru.otus.elena363404.repository.GenreRepository;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("GenreRepository with listener in context")
public class GenreRepositoryWithListenerTest {

  @Autowired
  private GenreRepository genreRepository;

  @Autowired
  private BookRepository bookRepository;

  @DisplayName("When deleting Genre should remove it from the Book")
  @Test
  void shouldRemoveGenreFromBookWhenRemovingGenre() {

    val books = bookRepository.findAll();
    val book = books.get(0);
    val genre = book.getGenre();

    // delete genre
    genreRepository.delete(genre);

    // load a book and checking its genre
    val actualBookOptional = bookRepository.findById(book.getId());

    Assertions.assertThat(actualBookOptional)
      .isNotEmpty().get()
      .matches(s -> s.getGenre() == null);
  }
}
