package ru.otus.elena363404.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ComponentScan({"ru.otus.elena363404.repository"})
class CommentRepositoryTest {

  private static final String EXISTING_COMMENT_ID = "2";
  private static final String EXISTING_BOOK_ID = "1";
  private static final String COMMENT_ID_FOR_DELETE = "3";
  private static final String COMMENT_ID_FOR_UPDATE = "3";
  private static final int EXPECTED_NUMBER_OF_COMMENTS = 4;

  @Autowired
  private CrudRepository<Comment, String> cr;

  @Autowired
  private CrudRepository<Book, String> crb;

  @Autowired
  private CommentRepository commentRepository;

  @DisplayName("Add comment in the DB")
  @Test
  void shouldInsertComment() {
    Book book = crb.findById("2").stream().findFirst().orElse(null);
    Comment expectedComment = new Comment("4", "Norm", book);
    commentRepository.save(expectedComment);
    Comment actualComment = cr.findById(expectedComment.getId()).stream().findFirst().orElse(null);
    assertThat(actualComment).isEqualTo(expectedComment);
  }

  @DisplayName("Return comment by ID")
  @Test
  void shouldReturnExpectedCommentById() {
    val optionalActualComment = commentRepository.findById(EXISTING_COMMENT_ID);
    val expextedComment = cr.findById(EXISTING_COMMENT_ID);
    assertThat(optionalActualComment).isEqualTo(expextedComment);
  }

  @DisplayName("Return comment by book ID")
  @Test
  void shouldReturnExpectedCommentByBookId() {
    Book book = crb.findById(EXISTING_BOOK_ID).stream().findFirst().orElse(null);
    List<Comment> optionalActualCommentList = commentRepository.findByBook(book);
    List<Comment> commentList = getCommentListByBookId(EXISTING_BOOK_ID);
    assertThat(commentList).isEqualTo(optionalActualCommentList);
  }

  @DisplayName("Update comment by ID")
  @Test
  void shouldUpdateExpectedCommentById() {
    Comment newComment = new Comment(COMMENT_ID_FOR_UPDATE, "Comment after update!", crb.findById("2").stream().findFirst().orElse(null));
    commentRepository.save(newComment);
    Comment updatedComment = cr.findById(COMMENT_ID_FOR_UPDATE).stream().findFirst().orElse(null);

    assertThat(newComment).isEqualTo(updatedComment);
  }

  @DisplayName("Delete comment by ID")
  @Test
  void shouldCorrectDeleteCommentById() {
    val commentBeforeDelete = cr.findById(COMMENT_ID_FOR_DELETE);
    assertNotNull(commentBeforeDelete);
    commentRepository.deleteById(COMMENT_ID_FOR_DELETE);
    val commentAfterDelete = cr.findById(COMMENT_ID_FOR_DELETE);
    assertThat(commentAfterDelete.isEmpty()).isEqualTo(true);
  }

  @DisplayName("Return list of the comments")
  @Test
  void shouldReturnExpectedCommentsList() {
    System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
    val comments = commentRepository.findAll();
    assertThat(comments).isNotNull().hasSize(EXPECTED_NUMBER_OF_COMMENTS)
      .allMatch(s -> !s.getComment().equals(""));
    System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
  }

  private List<Comment> getCommentListByBookId(String bookId) {
    Book book = crb.findById(bookId).stream().findFirst().orElse(null);
    List<Comment> commentList = new ArrayList<>();
    commentList.add(new Comment( "1", "Good book!", book));
    commentList.add(new Comment( "2", "Bad book!", book));

    return commentList;
  }
}