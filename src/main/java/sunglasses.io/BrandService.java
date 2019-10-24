package sunglasses.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {
  @Autowired
  BrandRepository brandRepository;

  public List<Brand> getAllBrands() {
    List<Brand> brands = new ArrayList<Brand>();
    brandRepository.findAll().forEach(brand -> brands.add(brand));
    return brands;
  }

  public List<Brand> searchBrands(String name) {
    List<Brand> brands = new ArrayList<>();
    brandRepository.findByNameIgnoreCase(name).forEach(brand -> brands.add(brand));
    return brands;
  }
}