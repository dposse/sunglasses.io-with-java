package sunglasses.io;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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