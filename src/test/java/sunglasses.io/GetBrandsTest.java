package sunglasses.io;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.json.Json;
import javax.json.JsonArray;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class GetBrandsTest {
  @LocalServerPort
  private int randomServerPort;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testGetBrandsSuccess() throws URISyntaxException {
    //arrange
    final String baseUrl = "http://localhost:" + randomServerPort + "/brands";
    URI uri = new URI(baseUrl);

    //act
    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

    //assert
    System.out.println(result.getStatusCodeValue());
    Assert.isTrue(result.getStatusCodeValue() == 200, "Response status must be 200");
  }

  @Test
  public void testGetBrandsReturnsList() throws URISyntaxException {
    //arrange
    final String baseUrl = "http://localhost:" + randomServerPort + "/brands";
    URI uri = new URI(baseUrl);
    //make empty json array to test against
    JsonArray expectedJSONArrayNotStrict = Json.createArrayBuilder().build();
    //below commented as no longer necessary
//    System.out.println("Value of expectedJSONArrayNotStrict:");
//    System.out.println(expectedJSONArrayNotStrict.toString());
    //act
    ResponseEntity<List<Brand>> response = restTemplate.exchange( baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Brand>>(){});
    //assert
    System.out.println(response);
//    JSONAssert.assertEquals(expectedJSONArrayNotStrict, response.getBody(), false);
    Assert.isTrue(response.getStatusCodeValue() == 200, "Response status must be 200");
    for (Brand brand : response.getBody()) {
      Assert.notNull(brand.getId(), "Brand ID should not be null");
      Assert.notNull(brand.getName(), "Brand name should not be null");
    }
  }
}