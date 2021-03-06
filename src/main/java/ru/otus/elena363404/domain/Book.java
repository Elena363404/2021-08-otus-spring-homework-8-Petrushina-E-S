package ru.otus.elena363404.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "book")
public class Book {

  @Id
  private String id;
  private String name;
  @DBRef
  private Author author;
  @DBRef
  private Genre genre;

  public Book(String name, Author author, Genre genre) {
    this.name = name;
    this.author = author;
    this.genre = genre;
  }

}

