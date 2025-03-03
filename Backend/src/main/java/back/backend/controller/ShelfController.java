package back.backend.controller;

import back.backend.exceptions.ResourceNotFoundException;
import back.backend.model.Book;
import back.backend.model.UserShelf;
import back.backend.repository.BookRepository;
import back.backend.repository.ShelfRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-shelves")
public class ShelfController{

    private final ShelfRepository repository;
    private final BookRepository BookRepository;
    public ShelfController(ShelfRepository repository, back.backend.repository.BookRepository bookRepository) {
        this.repository = repository;

        BookRepository = bookRepository;
    }

    @GetMapping
    public List<UserShelf> getAllUserShelf() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public UserShelf getUserShelfById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping
    public void createUserShelf(@RequestBody UserShelf us) {

        this.repository.save(us);
    }

    @PutMapping("/{id}")
    public void updateUserShelf(@PathVariable String id, @RequestBody UserShelf userShelf) {
        UserShelf us = repository.findById(id);

        if (userShelf != null) {
            repository.updateBook(id,userShelf.getId());
        } else {
            throw new ResourceNotFoundException("Book not found!");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUserShelf(@PathVariable String id) {repository.deleteById(id);
    }

    public int noOfBook(String id)
    {
        return this.repository.getNoOfBooks(id);
    }

    public void ShelvesAndBooks() {
        for(UserShelf us : this.getAllUserShelf())
            System.out.println("Book shelf with id: " + us.getId() + " has: " + noOfBook(us.getId()) +" books on it.\n");
    }

    @GetMapping("/{id}/books")
    public List<Book> getBooksByShelfId(@PathVariable String id) {
        UserShelf userShelf = repository.findById(id);
        if (userShelf == null) {
            throw new ResourceNotFoundException("Shelf not found with id: " + id);
        }
        // Call the method to find books by shelf id
        return this.repository.findBooksByShelfId(id);
    }

}