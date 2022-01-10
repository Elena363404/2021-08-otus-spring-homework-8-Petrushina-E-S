package ru.otus.elena363404.repository;

public interface GenreRepositoryCustom {

  void removeDeletedGenreFromBooks(String id);
}
