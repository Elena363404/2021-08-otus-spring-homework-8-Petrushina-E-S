package ru.otus.elena363404.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.repository.CrudRepository;
import ru.otus.elena363404.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class AuthorRepositoryTest {

  private static final String EXISTING_AUTHOR_ID = "3";
  private static final int EXPECTES_NUMBER_OF_AUTHORS = 4;
  private static final String AUTHOR_ID_FOR_DELETE = "5";

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private CrudRepository<Author, String> cr;

  @DisplayName("Add author in the DB")
  @Test
  void shouldInsertAuthor() {
    Author expectedAuthor = new Author("Lermontov");
    Author author = authorRepository.save(expectedAuthor);
    assertThat(expectedAuthor.getId()).isNotNull();
    assertThat(expectedAuthor.getName()).isEqualTo(author.getName());
  }

  @DisplayName("Return author by ID")
  @Test
  void shouldReturnExpectedAuthorById() {
    val optionalActualAuthor = authorRepository.findById(EXISTING_AUTHOR_ID);
    val expectedAuthor = cr.findById(EXISTING_AUTHOR_ID);
    assertThat(optionalActualAuthor).isEqualTo(expectedAuthor);
  }

  @DisplayName("Delete author by ID")
  @Test
  void shouldCorrectDeleteAuthorById() {
    val authorBeforeDelete = cr.findById(AUTHOR_ID_FOR_DELETE);
    assertNotNull(authorBeforeDelete);
    authorRepository.deleteById(AUTHOR_ID_FOR_DELETE);
    val authorAfterDelete = cr.findById(AUTHOR_ID_FOR_DELETE);
    assertThat(authorAfterDelete.isEmpty()).isEqualTo(true);
  }

  @DisplayName("Return list of the authors")
  @Test
  void shouldReturnExpectedAuthorsList() {
    System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
    val authors = authorRepository.findAll();
    assertThat(authors).isNotNull().hasSize(EXPECTES_NUMBER_OF_AUTHORS)
      .allMatch(s -> !s.getName().equals(""));
    System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
  }


}