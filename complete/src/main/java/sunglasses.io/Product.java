package sunglasses.io;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Product {
  //private vars
  private static AtomicInteger idCount = new AtomicInteger(0);
  @Id
  private int id;
  private int categoryId;
  @NotEmpty(message = "Please provide a product name")
  private String name;
  @NotEmpty(message = "Please provide a product description")
  private String description;
  private double price;
//  private ArrayList imageUrls;
//leaving out imageurls as sql db's shouldn't insert arrays directly

  //constructors
  public Product() {}

  public Product(int categoryId, String name, String description, double price) {
    this.id = idCount.incrementAndGet();
    this.categoryId = categoryId;
    this.name = name;
    this.price = price;
  }

  //getters
  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getDescription() {
    return description;
  }
  public double getPrice() { return price; }
}