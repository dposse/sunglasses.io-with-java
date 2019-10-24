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
import org.springframework.web.server.ResponseStatusException;

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
  public void testGetBrandsReturnsListOfBrandsWithCorrectProperties() throws URISyntaxException {
    //arrange
    final String baseUrl = "http://localhost:" + randomServerPort + "/brands";
    URI uri = new URI(baseUrl);
    //act
    ResponseEntity<List<Brand>> response = restTemplate.exchange( baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Brand>>(){});
    //assert
    Assert.isTrue(response.getStatusCodeValue() == 200, "Response status must be 200");
    for (Brand brand : response.getBody()) {
      Assert.notNull(brand.getId(), "Brand ID should not be null");
      Assert.notNull(brand.getName(), "Brand name should not be null");
    }
  }

  @Test
  public void testGetBrandsReturnsAllBrands() throws URISyntaxException {
    //arrange
    final String baseUrl = "http://localhost:" + randomServerPort + "/brands";
    URI uri = new URI(baseUrl);

    //act
    ResponseEntity<List<Brand>> response = restTemplate.exchange( baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Brand>>(){});

    //assert
    Assert.isTrue(response.getStatusCodeValue() == 200, "Response status must be 200");
    Assert.isTrue(response.getBody().size() == 5, "List of Brands should have 5 objects");
  }

  @Test
  public void testGetBrandsSearchOakley() throws URISyntaxException {
    //arrange
    final String searchTerm = "Oakley";
    final String baseUrl = "http://localhost:" + randomServerPort + "/brands";
    final String requestUrl = baseUrl + "?query=" + searchTerm;
    URI uri = new URI(requestUrl);

    //act
    ResponseEntity<List<Brand>> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Brand>>(){});

    //assert
    Assert.isTrue(response.getStatusCodeValue() == 200, "Response status must be 200");
    Assert.isTrue(response.getBody().get(0).getName().equalsIgnoreCase("Oakley"), "Should return one brand with name 'Oakley' when searching 'Oakley'");
  }

  @Test
  public void testGetBrandsSearchRayBan() throws URISyntaxException {
    //arrange
    final String searchTerm = "ray ban";
    final String baseUrl = "http://localhost:" + randomServerPort + "/brands";
    final String requestUrl = baseUrl + "?query=" + searchTerm;
    URI uri = new URI(requestUrl.replace(" ","%20"));

    //act
    ResponseEntity<List<Brand>> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Brand>>(){});

    //assert
    Assert.isTrue(response.getStatusCodeValue() == 200, "Response status must be 200");
    Assert.isTrue(response.getBody().get(0).getName().equalsIgnoreCase("Ray Ban"), "Should return one brand with name 'Ray Ban' when searching 'ray ban'");
  }

  @Test(expected = Re.class)
  public void testGetBrandsSearchBall() throws URISyntaxException {
    //arrange
    final String searchTerm = "ball";
    final String baseUrl = "http://localhost:" + randomServerPort + "/brands";
    final String requestUrl = baseUrl + "?query=" + searchTerm;
    URI uri = new URI(requestUrl);

    //act
    ResponseEntity<List<Brand>> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Brand>>(){});

    //assert
    Assert.isTrue(response.getStatusCodeValue() == 404, "Response status must be 404, there is no brand named ball");
  }
}