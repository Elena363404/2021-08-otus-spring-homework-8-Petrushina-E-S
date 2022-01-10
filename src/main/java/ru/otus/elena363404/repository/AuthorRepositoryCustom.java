package ru.otus.elena363404.repository;

public interface AuthorRepositoryCustom {

  void removeDeletedAuthorFromBooks(String id);
}
