package sunglasses.io;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import sunglasses.io.Brand;
import sunglasses.io.BrandRepository;

@Service
public class BrandService {
  @Autowired
  BrandRepository brandRepository;

  public List<Brand> getAllBrands() {
    List<Brand> brands = new ArrayList<Brand>();
    brandRepository.findAll().forEach(brand -> brands.add(brand));
    return brands;
  }
}