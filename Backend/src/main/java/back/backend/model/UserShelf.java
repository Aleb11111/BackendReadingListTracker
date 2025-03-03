package back.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class UserShelf {

    @Id
    private String id;

    private String shelfName;
    private int capacity;

    @OneToMany
    private List<Book> books;


    // Constructors, Getters, and Setters
    public UserShelf() {
        this.id = generateUniqueId();
    }

    public UserShelf(String shelfName, int capacity) {
        this.id = generateUniqueId();
        this.shelfName = shelfName;
        this.capacity = capacity;
    }

    // Generate unique ID using UUID
    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    // Id field doesn't need a setter, as it's generated
    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void displayBooks() {
        System.out.println("Books on Shelf " + shelfName + ":");
        if (books != null) {
            for (Book book : books) {
                System.out.println("Title: " + book.getBookTitle() + ", Author: " + book.getAuthor() + ", Pages: " + book.getNoOfPages());
            }
        } else {
            System.out.println("No books on this shelf.");
        }
    }

}
