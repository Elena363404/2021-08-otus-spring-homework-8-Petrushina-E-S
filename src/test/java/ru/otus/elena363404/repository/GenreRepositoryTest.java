package ru.otus.elena363404.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import ru.otus.elena363404.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataMongoTest
@ComponentScan({"ru.otus.elena363404.repository"})
class GenreRepositoryTest {

  private static final String EXISTING_GENRE_ID = "3";
  private static final String GENRE_ID_FOR_DELETE = "5";
  private static final int EXPECTES_NUMBER_OF_GENRES = 5;

  @Autowired
  private GenreRepository genreRepository;

  @Autowired
  private CrudRepository<Genre, String> cr;

  @DisplayName("Add genre in the DB")
  @Test
  void shouldInsertGenre() {
    Genre expectedGenre = new Genre( "Thriller");
    Genre genre = genreRepository.save(expectedGenre);
    assertThat(expectedGenre.getId()).isNotNull();
    assertThat(expectedGenre.getName()).isEqualTo(genre.getName());
  }

  @DisplayName("Return genre by ID")
  @Test
  void shouldReturnExpectedGenreById() {
    val optionalActualGenre = genreRepository.findById(EXISTING_GENRE_ID);
    val expectedGenre = cr.findById(EXISTING_GENRE_ID);
    assertThat(optionalActualGenre).isEqualTo(expectedGenre);
  }

  @DisplayName("Delete genre by ID")
  @Test
  void shouldCorrectDeleteGenreById() {
    val genreBeforeDelete = cr.findById(GENRE_ID_FOR_DELETE);
    assertNotNull(genreBeforeDelete);
    genreRepository.deleteById(GENRE_ID_FOR_DELETE);
    val genreAfterDelete = cr.findById(GENRE_ID_FOR_DELETE);
    assertThat(genreAfterDelete.isEmpty()).isEqualTo(true);
  }

  @DisplayName("Return list of the genres")
  @Test
  void shouldReturnExpectedGenresList() {
    System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
    val genres = genreRepository.findAll();
    assertThat(genres).isNotNull().hasSize(EXPECTES_NUMBER_OF_GENRES)
      .allMatch(s -> !s.getName().equals(""));
    System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
  }

}