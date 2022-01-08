package ru.otus.elena363404.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.elena363404.domain.Author;

@RequiredArgsConstructor
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public void removeBookListByAuthorId(String id) {
    val query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
    val update = new Update().pull("book", query);
    mongoTemplate.updateMulti(new Query(), update, Author.class);
  }

}
