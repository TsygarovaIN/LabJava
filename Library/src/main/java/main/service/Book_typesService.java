package main.service;

import main.entity.Book_types;

import java.util.List;

public interface Book_typesService {
    List<Book_types> listBookTypes();
    Book_types findBookType(int id);
    Book_types addBookType(Book_types bookType);
    void deleteBookType(int id);
}
