package ru.otus.elena363404.repository;

public interface BookRepositoryCustom {

  void removeDeletedBookFromComments(String id);
}
