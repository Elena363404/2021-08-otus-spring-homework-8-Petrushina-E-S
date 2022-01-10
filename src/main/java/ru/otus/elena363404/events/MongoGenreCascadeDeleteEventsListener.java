package ru.otus.elena363404.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.repository.GenreRepository;

@Component
@RequiredArgsConstructor
public class MongoGenreCascadeDeleteEventsListener extends AbstractMongoEventListener<Genre> {

    private final GenreRepository genreRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Genre> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        val id = source.get("_id").toString();
        genreRepository.removeDeletedGenreFromBooks(id);
    }
}
