package main.web;

import main.entity.BookTypes;
import main.entity.Books;
import main.entity.Clients;
import main.entity.Journal;
import main.exeption.BookTypesNotFoundExeption;
import main.exeption.BooksNotFoundExeption;
import main.exeption.ClientsNotFoundExeption;
import main.exeption.JournalNotFoundExeption;
import main.service.BookTypesService;
import main.service.BooksService;
import main.service.ClientsService;
import main.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private JournalService journalService;
    @Autowired
    private ClientsService clientsService;
    @Autowired
    private BookTypesService bookTypesService;
    @Autowired
    private BooksService booksService;

    public LibraryController(JournalService journalService, ClientsService clientService,
                             BooksService bookService, BookTypesService bookTypeService) {
        this.journalService = journalService;
        this.clientsService = clientService;
        this.booksService = bookService;
        this.bookTypesService = bookTypeService;
    }


    @GetMapping("/journal")
    public ResponseEntity<List<Journal>> getAllJournal() {
        List<Journal> list = journalService.journalList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/journal/{id}")
    public ResponseEntity<Journal> getJournal(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(journalService.findJournal(id), HttpStatus.OK);
        } catch (JournalNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journal record not found");
        }
    }

    @PostMapping(value = "/addJournal", consumes = "application/json", produces = "application/json")
    public Journal addJournal(@RequestBody Journal newJournal){
        return journalService.addJournal(newJournal);
    }

    @DeleteMapping("/deleteJournal/{id}")
    public void deleteJournal(@PathVariable("id") Long id) {
        try {
            journalService.deleteJournal(id);
        } catch (JournalNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journal not found");
        }
    }

    @PutMapping(value = "/updateJournal/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Journal> updateJournal(@PathVariable("id") Long id, @RequestBody Journal newJournal) {
        try {
            Journal updatedJournal = journalService.findJournal(id);
            Long newBookId = newJournal.getBookId();
            Long newClientId = newJournal.getClientId();
            Timestamp newDateBeg = newJournal.getDateBeg();
            Timestamp newDateEnd = newJournal.getDateEnd();
            Timestamp newDateRet = newJournal.getDateRet();

            if (newBookId != null)
                updatedJournal.setBookId(newBookId);
            if (newClientId != null)
                updatedJournal.setClientId(newClientId);
            if (newDateBeg != null)
                updatedJournal.setDateBeg(newDateBeg);
            if (newDateEnd != null)
                updatedJournal.setDateEnd(newDateEnd);
            if (newDateRet != null)
                updatedJournal.setDateRet(newDateRet);

            return ResponseEntity.ok(journalService.addJournal(updatedJournal));
        } catch (JournalNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journal not found");
        }
    }

    //-------------------------CLIENTS---------------------------------

    @GetMapping("/clients")
    public ResponseEntity<List<Clients>> getAllClients() {
        List<Clients> list = clientsService.listClients();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Clients> getClient(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(clientsService.findClient(id), HttpStatus.OK);
        } catch (ClientsNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }
    }

    @PostMapping(value = "/addClient", consumes = "application/json", produces = "application/json")
    public Clients addClients(@RequestBody Clients newClients){
        return clientsService.addClient(newClients);
    }

    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable("id") Long id) {
        try {
            clientsService.deleteClient(id);
        } catch (ClientsNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }
    }

    @PutMapping(value = "/updateClient/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Clients> updateClient(@PathVariable("id") Long id, @RequestBody Clients newClient) {
        try {
            Clients updatedClient = clientsService.findClient(id);
            String firstName = newClient.getFirstName();
            String lastName = newClient.getLastName();
            String patherName = newClient.getPatherName();
            String passportSeria = newClient.getPassportSeria();
            String passportNum = newClient.getPassportNum();

            if (firstName != null)
                updatedClient.setFirstName(firstName);
            if (lastName != null)
                updatedClient.setLastName(lastName);
            if (patherName != null)
                updatedClient.setPatherName(patherName);
            if (passportSeria != null)
                updatedClient.setPassportSeria(passportSeria);
            if (passportNum != null)
                updatedClient.setPassportNum(passportNum);

            return ResponseEntity.ok(clientsService.addClient(updatedClient));
        } catch (ClientsNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }
    }

    //--------------------BOOK TYPES--------------------------------

    @GetMapping("/bookTypes")
    public ResponseEntity<List<BookTypes>> getAllBookTypes() {
        List<BookTypes> list = bookTypesService.listBookTypes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/bookType/{id}")
    public ResponseEntity<BookTypes> getBookTypes(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(bookTypesService.findBookType(id), HttpStatus.OK);
        } catch (BookTypesNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book types not found");
        }
    }

    @PostMapping(value = "/addBookType", consumes = "application/json", produces = "application/json")
    public BookTypes addBookTypes(@RequestBody BookTypes newBookTypes){
        return bookTypesService.addBookType(newBookTypes);
    }

    @DeleteMapping("/deleteBookType/{id}")
    public void deleteBookTypes(@PathVariable("id") Long id) {
        try {
            bookTypesService.deleteBookType(id);
        } catch (BookTypesNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book types not found");
        }
    }

    @PutMapping(value = "/updateBookType/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookTypes> updateBookTypes(@PathVariable("id") Long id, @RequestBody BookTypes newBookType) {
        try {
            BookTypes updatedBookType = bookTypesService.findBookType(id);
            String name = newBookType.getName();
            Long cnt  = newBookType.getCnt();
            Long fine = newBookType.getFine();
            Long dayCount = newBookType.getDayCount();

            if (name != null)
                updatedBookType.setName(name);
            if (cnt != null)
                updatedBookType.setCnt(cnt);
            if (fine != null)
                updatedBookType.setFine(fine);
            if (dayCount != null)
                updatedBookType.setDayCount(dayCount);

            return ResponseEntity.ok(bookTypesService.addBookType(updatedBookType));
        } catch (BookTypesNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book types not found");
        }
    }

    //----------------------BOOKS------------------------

    @GetMapping("/books")
    public ResponseEntity<List<Books>> getAllBooks() {
        return new ResponseEntity<>(booksService.listBooks(), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Books> getBook(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(booksService.findBook(id), HttpStatus.OK);
        } catch (BooksNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @PostMapping(value = "/addBook", consumes = "application/json", produces = "application/json")
    public Books addBook(@RequestBody Books newBook){
        return booksService.addBook(newBook);
    }

    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        try {
            booksService.deleteBook(id);
        } catch (BooksNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @PutMapping(value = "/updateBook/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Books> updateBook(@PathVariable("id") Long id, @RequestBody Books newBook) {
        try {
            Books updatedBook = booksService.findBook(id);
            String name = newBook.getName();
            Long cnt = newBook.getCnt();
            Long typeId = newBook.getTypeId();

            if (name != null)
                updatedBook.setName(name);
            if (cnt != null)
                updatedBook.setCnt(cnt);
            if (typeId != null)
                updatedBook.setTypeId(typeId);

            return ResponseEntity.ok(booksService.addBook(updatedBook));
        } catch (BooksNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }
}
