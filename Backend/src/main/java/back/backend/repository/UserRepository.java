
package back.backend.repository;

import back.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}


//package back.backend.repository;
//
//import back.backend.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.Collections;
//import java.util.List;
//
//@Repository
//public class UserRepository {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public List<User> findAll() {
//        try {
//            return jdbcTemplate.query("SELECT * FROM User", new BeanPropertyRowMapper<>(User.class));
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//            return Collections.emptyList();
//        }
//    }
//
//    public User findById(Long id) {
//        try {
//            return jdbcTemplate.queryForObject("SELECT * FROM User WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public User findByUsername(String username) {
//        try {
//            return jdbcTemplate.queryForObject("SELECT * FROM User WHERE username = ?", new Object[]{username}, new BeanPropertyRowMapper<>(User.class));
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public void save(User user) {
//        try {
//            jdbcTemplate.update("INSERT INTO User (username, email, password) VALUES (?, ?, ?)",
//                    user.getUsername(), user.getEmail(), user.getPassword());
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void update(User user) {
//        try {
//            jdbcTemplate.update("UPDATE User SET username = ?, email = ?, password = ? WHERE id = ?",
//                    user.getUsername(), user.getEmail(), user.getPassword(), user.getId());
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteById(Long id) {
//        try {
//            jdbcTemplate.update("DELETE FROM User WHERE id = ?", id);
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // You can implement additional methods as needed
//}
