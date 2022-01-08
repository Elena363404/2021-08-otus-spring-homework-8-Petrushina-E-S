package ru.otus.elena363404.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {

  private final BookRepository bookRepository;

  @Override
  public void onAfterDelete(AfterDeleteEvent<Book> event) {
    super.onAfterDelete(event);
    val source = event.getSource();
    val id = source.get("_id").toString();
    bookRepository.removeCommentListByBookId(id);
  }
}
