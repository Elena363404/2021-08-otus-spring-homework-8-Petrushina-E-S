package ru.otus.elena363404.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public void removeDeletedBookFromComments(String id) {
    val query = Query.query(Criteria.where("book.$id").is(new ObjectId(id)));
    val update = new Update().unset("book");
    mongoTemplate.updateMulti(query, update, Comment.class);
  }
}
