package main.service;

import main.entity.Book_types;
import main.exeption.Book_typesNotFoundExeption;
import main.repos.Book_typesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Book_typesServiceImpl implements Book_typesService{

    @Autowired
    private Book_typesRepository bookTypeRepository;

    @Override
    public List<Book_types> listBookTypes() {
        return (List<Book_types>) bookTypeRepository.findAll();
    }

    @Override
    public Book_types findBookType(int id) {
        Optional<Book_types> optionalBookType = bookTypeRepository.findById((long)id);
        if (optionalBookType.isPresent()) {
            return optionalBookType.get();
        } else {
            throw new Book_typesNotFoundExeption("Book type  not found");
        }
    }

    @Override
    public Book_types addBookType(Book_types bookType ) {
        return bookTypeRepository.save(bookType);
    }

    @Override
    public void deleteBookType(int id) {
        Optional<Book_types> optionalBookType = bookTypeRepository.findById((long) id);
        if (optionalBookType.isPresent()) {
            bookTypeRepository.delete(optionalBookType.get());
        } else {
            throw new Book_typesNotFoundExeption("Book type not found");
        }
    }
}
