package ru.otus.elena363404.withlisteners;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.repository.BookRepository;
import ru.otus.elena363404.repository.CommentRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan("ru.otus.elena363404.events")
@DisplayName("BookRepository with listener in context")
public class BookRepositoryWithListenerTest {

  private static final int cntCommentByBookBeforeDelete = 2;
  private static final int cntCommentByBookAfterDelete = 0;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private CommentRepository commentRepository;

  @DisplayName("When deleting Book should remove it from the Comment")
  @Test
  void shouldRemoveBookFromCommentWhenRemovingBook() {

    Book book = bookRepository.findAll().get(0);
    int cntCommentByBook = (commentRepository.findByBook(book)).size();

    assertNotNull(book);
    assertThat(cntCommentByBook).isEqualTo(cntCommentByBookBeforeDelete);

    // delete book
    bookRepository.delete(book);

    cntCommentByBook = (commentRepository.findByBook(book)).size();

    assertThat(cntCommentByBook).isEqualTo(cntCommentByBookAfterDelete);
  }
}
