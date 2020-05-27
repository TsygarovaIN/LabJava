package main.exeption;

public class Book_typesNotFoundExeption extends RuntimeException{
    public Book_typesNotFoundExeption(String message) {
        super(message);
    }
}
