package ru.otus.elena363404.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.elena363404.domain.Book;

@RequiredArgsConstructor
public class GenreRepositoryCustomImpl implements GenreRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public void removeDeletedGenreFromBooks(String id) {
    val query = Query.query(Criteria.where("genre.$id").is(new ObjectId(id)));
    val update = new Update().unset("genre");
    mongoTemplate.updateMulti(query, update, Book.class);
  }

}
