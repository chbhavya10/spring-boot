package jbr.spring.boot.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jbr.spring.boot.model.Product;

@Repository
public class ProductDao {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public void executeQuery(String query) {
    jdbcTemplate.execute(query);
  }

  public void addProduct() {
    jdbcTemplate.execute("INSERT INTO product VALUES('100','Samsung S8', 'Mobile', '75000')");
    jdbcTemplate.execute("INSERT INTO product VALUES('200','Usha Fan', 'Fan', '6000')");
    jdbcTemplate.execute("INSERT INTO product VALUES('300','Dell Vostro', 'Laptop', '79000')");
  }

  public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    jdbcTemplate.query("SELECT * FROM product", new Object[] {},
        (rs, row) -> new Product(rs.getString("id"), rs.getString("name"), rs.getString("type"), rs.getString("price")))
        .forEach(products::add);

    return products;
  }
}
