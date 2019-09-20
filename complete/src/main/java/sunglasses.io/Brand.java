package sunglasses.io;

import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Brand {
  private static AtomicInteger idCount = new AtomicInteger(0);
  @Id
  private int id;
  @NotEmpty(message = "Please provide a brand name")
  private String name;

  //constructors
  public Brand() {}

  public Brand(String name) {
    this.id = idCount.incrementAndGet();
    this.name = name;
  }

  //getters
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}