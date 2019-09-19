package sunglasses.io;

import java.util.concurrent.atomic.AtomicInteger;

public class Brand {
  private static AtomicInteger idCount = new AtomicInteger(0);

  private int id;
  private final String name;

  public Brand(String name) {
    this.id = idCount.incrementAndGet();
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}