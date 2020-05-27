package main.exeption;

public class ClientsNotFoundExeption extends RuntimeException{
    public ClientsNotFoundExeption(String message) {
        super(message);
    }
}
