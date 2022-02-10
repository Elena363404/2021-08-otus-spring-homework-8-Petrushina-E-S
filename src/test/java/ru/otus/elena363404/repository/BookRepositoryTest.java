package ru.otus.elena363404.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.repository.CrudRepository;
import ru.otus.elena363404.changelogTest.MongoIdForTest;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class BookRepositoryTest {

  private static final String EXISTING_BOOK_ID = MongoIdForTest.idBook3;
  private static final int EXPECTED_NUMBER_OF_BOOKS = 4;
  private static final String BOOK_ID_FOR_DELETE = MongoIdForTest.idBook2;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private CrudRepository<Book, String> cr;

  @DisplayName("Add book in the DB")
  @Test
  void shouldInsertBook() {
    Book expectedBook = new Book( MongoIdForTest.idBook5, "BookForTest", new Author(MongoIdForTest.idAuthor2, "Alexander Pushkin"), new Genre(MongoIdForTest.idGenre3, "Novel"));
    Book newBook = bookRepository.save(expectedBook);
    val actualBook = cr.findById(newBook.getId()).stream().findFirst().orElse(null);
    assertThat(actualBook).isEqualTo(expectedBook);
  }

  @DisplayName("Return book by ID")
  @Test
  void shouldReturnExpectedBookById() {
    val optionalActualBook = bookRepository.findById(EXISTING_BOOK_ID);
    val expectedBook = cr.findById(EXISTING_BOOK_ID);
    assertThat(optionalActualBook).isEqualTo(expectedBook);
  }

  @DisplayName("Delete book by ID")
  @Test
  void shouldCorrectDeleteBookById() {
    val bookBeforeDelete = cr.findById(BOOK_ID_FOR_DELETE);
    assertNotNull(bookBeforeDelete);
    bookRepository.deleteById(BOOK_ID_FOR_DELETE);
    val bookAfterDelete = cr.findById(BOOK_ID_FOR_DELETE);
    assertThat(bookAfterDelete.isEmpty()).isEqualTo(true);
  }

  @DisplayName("Return list of the books")
  @Test
  void shouldReturnExpectedBooksList() {
    System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
    val books = bookRepository.findAll();
    assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
      .allMatch(s -> !s.getName().equals(""))
      .allMatch(s -> s.getAuthor() != null)
      .allMatch(s -> s.getGenre() != null);
    System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
  }
}