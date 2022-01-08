package ru.otus.elena363404.withlisteners;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.elena363404.repository.AuthorRepository;
import ru.otus.elena363404.repository.BookRepository;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("AuthorRepository with listener in context")
public class AuthorRepositoryWithListenerTest{

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private BookRepository bookRepository;

  @DisplayName("When deleting Author should remove it from the Book")
  @Test
  void shouldRemoveAuthorFromBookWhenRemovingAuthor() {

    val books = bookRepository.findAll();
    val book = books.get(0);
    val author = book.getAuthor();

    // delete author
    authorRepository.delete(author);

    // load a book and checking its author
    val actualBookOptional = bookRepository.findById(book.getId());

    Assertions.assertThat(actualBookOptional)
      .isNotEmpty().get()
      .matches(s -> s.getAuthor() == null);
  }

}
