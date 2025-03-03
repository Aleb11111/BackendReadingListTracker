package back.backend.model;

import back.backend.model.Book;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getString("id"));
        book.setBookTitle(rs.getString("bookTitle"));
        book.setAuthor(rs.getString("author"));
        book.setNoOfPages(rs.getInt("noOfPages"));
        return book;
    }
}