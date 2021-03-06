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
@Document(collection = "comment")
public class Comment {

  @Id
  private String id;
  private String comment;
  @DBRef
  private Book book;

  public Comment(String comment, Book book) {
    this.comment = comment;
    this.book = book;
  }


}
