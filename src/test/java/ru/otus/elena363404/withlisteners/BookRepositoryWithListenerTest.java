package ru.otus.elena363404.withlisteners;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.elena363404.domain.Comment;
import ru.otus.elena363404.repository.BookRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("BookRepository with listener in context")
public class BookRepositoryWithListenerTest {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private MongoOperations operations;

  @DisplayName("When deleting Book should remove it from the Comment")
  @Test
  void shouldRemoveBookFromCommentWhenRemovingBook() {

    val comments = operations.findAll(Comment.class);
    val comment = comments.get(0);
    val book = comment.getBook();

    // delete book
    bookRepository.delete(book);

    // load a comment and checking its book
    val actualCommentOptional = operations.findById(comment.getId(), Comment.class);

    assertThat(actualCommentOptional != null && actualCommentOptional.getBook() == null);
  }
}
