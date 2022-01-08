package ru.otus.elena363404.withlisteners;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.elena363404.repository.BookRepository;
import ru.otus.elena363404.repository.CommentRepository;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("BookRepository with listener in context")
public class BookRepositoryWithListenerTest {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private BookRepository bookRepository;

  @DisplayName("When deleting Book should remove it from the Comment")
  @Test
  void shouldRemoveBookFromCommentWhenRemovingBook() {

    val comments = commentRepository.findAll();
    val comment = comments.get(0);
    val book = comment.getBook();

    // delete book
    bookRepository.delete(book);

    // load a comment and checking its book
    val actualCommentOptional = commentRepository.findById(comment.getId());

    Assertions.assertThat(actualCommentOptional)
      .isNotEmpty().get()
      .matches(s -> s.getBook() == null);
  }
}
