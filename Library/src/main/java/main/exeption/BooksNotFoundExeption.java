package main.exeption;

public class BooksNotFoundExeption extends RuntimeException {
    public BooksNotFoundExeption(String message) {
        super(message);
    }
}
