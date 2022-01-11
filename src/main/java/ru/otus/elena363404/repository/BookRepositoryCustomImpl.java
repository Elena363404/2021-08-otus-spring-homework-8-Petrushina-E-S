package ru.otus.elena363404.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.elena363404.domain.Comment;


@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public void removeCommentForDeletedBook(String id) {
    val query = Query.query(Criteria.where("book.$id").is(new ObjectId(id)));
    mongoTemplate.remove(query, Comment.class);
  }
}
