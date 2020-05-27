package main.web;

import main.repos.Book_typesRepository;
import main.repos.BooksRepository;
import main.repos.ClientsRepository;
import main.repos.JournalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class TemplatesController {
    private JournalRepository journalRepository;
    private ClientsRepository clientsRepository;
    private BooksRepository booksRepository;
    private Book_typesRepository bookTypesRepository;

    @GetMapping("/journal")
    public String journal(Map<String, Object> model) {
        model.put("journalList", journalRepository.findAll());
        return "Journal";
    }

    @GetMapping("/clients")
    public String clients(Map<String, Object> model) {
        model.put("clientsList", clientsRepository.findAll());
        return "Clients";
    }

    @GetMapping("/books")
    public String books(Map<String, Object> model) {
        model.put("booksList", booksRepository.findAll());
        return "Books";
    }

    @GetMapping("/bookTypes")
    public String bookTypes(Map<String, Object> model) {
        model.put("bookTypesList", bookTypesRepository.findAll());
        return "BookTypes";
    }
}
