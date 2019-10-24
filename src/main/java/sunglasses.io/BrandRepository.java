package sunglasses.io;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
  List<Brand> findByNameIgnoreCase(String name);
}