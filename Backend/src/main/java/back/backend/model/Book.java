package back.backend.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Book {

    @Id
    private String id;
    private String author;
    private String bookTitle;
    private int noOfPages;

    private String shelfid;

    public Book() {

    }
    // Constructors, Getters, and Setters
    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
    public Book(String title, String author, int numberOfPages, String shelfid) {
        this.shelfid = shelfid;
        this.id = generateUniqueId();
        this.bookTitle = title;
        this.author = author;
        this.noOfPages = numberOfPages;
        this.shelfid=shelfid;
    }

    // Getters and Setters
    // Id field doesn't need a setter, as it's generated
    public String  getId() {
        return id;
    }

    public void setBookTitle(String title) {
        this.bookTitle = title;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setID(){
        this.id = generateUniqueId();
    }
    public void setNoOfPages(int numberOfPages) {
        this.noOfPages = numberOfPages;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setId(String id) {
        this.id=id;
    }

}
