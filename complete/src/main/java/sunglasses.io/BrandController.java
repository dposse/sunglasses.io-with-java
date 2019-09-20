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
  private BrandRepository repository;

  //get all brands, return array
  @GetMapping(value = "/brands", produces = MediaType.APPLICATION_JSON_VALUE)
  List<Brand> getBrands() {
    return null;
  }
}