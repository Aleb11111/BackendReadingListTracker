package back.backend.repository;

import back.backend.model.Book;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> findAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Book findByTitle(String title) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Book WHERE book_title = ?", new Object[]{title}, new BeanPropertyRowMapper<>(Book.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Book findById(String id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM book WHERE book_title = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(Book book) {
        try {
            jdbcTemplate.update("INSERT INTO book (id, book_title, author, no_of_pages) VALUES (?, ?, ?, ?)",
                    book.getId(), book.getBookTitle(), book.getAuthor(), book.getNoOfPages());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(String title, String newTitle, String newAuthor, int newNoOfPages) {
        try {
            jdbcTemplate.update("UPDATE book SET book_title = ?, author = ?, no_of_pages = ? WHERE book_title = ?",
                    newTitle, newAuthor, newNoOfPages, title);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public void deleteByTitle(String title) {
        try {
            jdbcTemplate.update("DELETE FROM book WHERE book_title = ?", title);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean isBookBanned(String title) {
        try {
            Book book = findByTitle(title);
            if (book != null) {
                // Check if the book is banned (you need to implement this logic)
                // For demonstration purposes, assume it's banned if the book title contains "banned"
                return book.getBookTitle().toLowerCase().contains("banned");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false; // Return false if the book is not found or if an exception occurs
    }

    public int noOfShelves(String id)
    {
        return jdbcTemplate.update("Select COUNT(*) FROM usershelf WHERE id=?" , id);
    }
    public void BooksOnShelves()
    {
        for(Book b : this.findAll())
        {
            System.out.println("Book shelf with id: " + b.getBookTitle() + " is on: " + noOfShelves(b.getId()) +" shelves.\n");
        }
    }

    // You can implement additional methods as needed

}





//package back.backend.repository;
//
//import back.backend.model.Book;
//import back.backend.model.BookRowMapper;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class BookRepository {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    private List<Book> books;
//
//    public BookRepository() {
//        init();
//    }
//
//    public List<Book> bannedBooks;
//    private void init() {
////        books = new ArrayList<>(Arrays.asList(
////                new Book("Fourth Wing", "Rebbeca Yarros", 321),
////                new Book("Ion", "Liviu Rebreanu", 248),
////                new Book("A Game of Thrones", "George R. R. Martin", 912),
////                new Book("The Two Towers", "J. R. R. Tolkien", 452)
////        ));
////
////        bannedBooks = new ArrayList<>(Arrays.asList(
////                new Book("The Bluest Eye","Toni Morrison",322),
////                new Book("Gender Queer: A Memoir","Maia Kobabe",122),
////                new Book("Of Mice and Men","John Steinbeck",451)
////        ));
//
//    }
//
//    public List<Book> findAll() {
////        try {
////            this.books= jdbcTemplate.query("SELECT * FROM Books", new BookRowMapper());
////            return this.books;
////        } catch (DataAccessException e) {
////            // Handle or log the exception
////            e.printStackTrace();
////            return Collections.emptyList(); // Return an empty list or handle the error accordingly
////        }
//        return this.books;
//    }
//
//    public Book findById(String id) {
//        for (Book book : books) {
//            if (book.getId().equals(id)) {
//                return book;
//            }
//        }
//        return null; // Or throw an exception if not found
//    }
//
//    public void save(Book book) {
//
//        books.add(book);
//    }
//    public void updateBook(String title, String newTitle, String newAuthor, int newNoOfPages) {
//        for (Book book : books) {
//            if (book.getBookTitle().equals(title)) {
//                book.setBookTitle(newTitle);
//                book.setAuthor(newAuthor);
//                book.setNoOfPages(newNoOfPages);
//                return;
//            }
//        }
//    }
//    public void deleteByTitle(String title) {
//        books.removeIf(book -> book.getBookTitle().equals(title));
//    }
//
//    public boolean isItBanned(String title)
//    {
//        for (Book book : bannedBooks) {
//            if (book.getBookTitle().equals(title)) {
//                return true;
//            }
//        }
//        return false;
//    }
//    public Book findByTitle(String title) {
//        for (Book book : books) {
//            if (book.getBookTitle().equals(title)) {
//                return book;
//            }
//        }
//        return null;
//    }
//
//    public List<Book> getBanned()
//    {
//        return this.bannedBooks;
//    }
//
//    // Method to generate and add random books
//    public void generateRandomBooks(int numberOfBooks) {
//        Random random = new Random();
//        for (int i = 0; i < numberOfBooks; i++) {
//            // Generate random book details
//            String title = "Title " + (i + 1);
//            String author = "Author " + (i + 1);
//            int numberOfPages = random.nextInt(500) + 100; // Generate random number of pages between 100 and 600
//
//            // Create and save the random book with a unique identifier generated using UUID
//            Book book = new Book(title, author, numberOfPages);
//            save(book);
//        }
//    }
//
//}
//
//
