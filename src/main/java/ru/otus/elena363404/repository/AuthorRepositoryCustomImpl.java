package ru.otus.elena363404.repository;

import com.mongodb.BasicDBObject;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;

@RequiredArgsConstructor
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public void removeDeletedAuthorFromBooks(String id) {
    val query = Query.query(Criteria.where("author.$id").is(new ObjectId(id)));
    val update = new Update().unset("author");
    mongoTemplate.updateMulti(query, update, Book.class);

  }

}
