package sunglasses.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BrandController {
  @Autowired
  BrandService brandService;

  //get all brands, return array
  @GetMapping("/brands")
  List<Brand> getAllBrands() {
    return brandService.getAllBrands();
  }
}