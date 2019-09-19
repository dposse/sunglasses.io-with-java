package sunglasses.io;

public class Brand {
  private static final AtomicInteger id = new AtomicInterger(0);
  private final String name;

  public Brand(String name) {
    this.id = id.incrementAndGet();
    this.name = name;
  }

  public static AtomicInteger getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}