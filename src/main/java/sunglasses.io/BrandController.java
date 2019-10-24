package sunglasses.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BrandController {
  @Autowired
  BrandService brandService;

  @GetMapping("/brands")
  List<Brand> getAllBrands(@RequestParam(required = false) String query) {
    //search for a specific brand, search query sent by get request query
    // i.e. .../brands?query=Burberry
    if (query != null) {
      List<Brand> brandsFound = brandService.searchBrands(query);
      if (brandsFound.size() == 0)
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Brand not found"
        );

      else
        return brandsFound;
    }

    //else all brands, return array
    return brandService.getAllBrands();
  }
}